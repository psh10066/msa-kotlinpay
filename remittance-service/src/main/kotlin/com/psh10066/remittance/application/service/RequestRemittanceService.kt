package com.psh10066.remittance.application.service

import com.psh10066.common.annotation.UseCase
import com.psh10066.common.exception.CustomException
import com.psh10066.common.exception.ErrorType
import com.psh10066.remittance.adapter.out.persistence.RemittanceRequestMapper
import com.psh10066.remittance.adapter.out.persistence.RemittanceStatus
import com.psh10066.remittance.application.port.`in`.RemittanceType
import com.psh10066.remittance.application.port.`in`.RequestRemittanceCommand
import com.psh10066.remittance.application.port.`in`.RequestRemittanceUseCase
import com.psh10066.remittance.application.port.out.RequestRemittancePort
import com.psh10066.remittance.application.port.out.banking.BankingPort
import com.psh10066.remittance.application.port.out.membership.MembershipPort
import com.psh10066.remittance.application.port.out.money.MoneyPort
import com.psh10066.remittance.domain.RemittanceRequest
import org.springframework.transaction.annotation.Transactional
import kotlin.math.ceil

@UseCase
@Transactional
class RequestRemittanceService(
    private val requestRemittancePort: RequestRemittancePort,
    private val remittanceRequestMapper: RemittanceRequestMapper,
    private val membershipPort: MembershipPort,
    private val moneyPort: MoneyPort,
    private val bankingPort: BankingPort
) : RequestRemittanceUseCase {
    override fun requestRemittance(command: RequestRemittanceCommand): RemittanceRequest? {

        val entity = requestRemittancePort.createRemittanceRequestHistory(command)

        val membershipStatus = membershipPort.getMembershipStatus(command.fromMembershipId!!)
        if (membershipStatus?.isValid != true) {
            throw CustomException(ErrorType.UNAUTHORIZED_MEMBER)
        }

        val moneyInfo = moneyPort.getMoneyInfo(command.fromMembershipId)
        if (moneyInfo.balance < command.amount!!) {
            val rechargeAmount = (ceil(command.amount - moneyInfo.balance / 10000.0) * 10000).toInt()
            val moneyResult = moneyPort.requestMoneyRecharging(command.fromMembershipId, rechargeAmount)
            if (!moneyResult) {
                throw CustomException(ErrorType.LACK_ACCOUNT_BALANCE)
            }
        }

        when (command.remittanceType) {
            RemittanceType.MEMBERSHIP -> {
                moneyPort.requestMoneyDecrease(
                    membershipId = command.fromMembershipId,
                    amount = command.amount
                )
                moneyPort.requestMoneyIncrease(
                    membershipId = command.toMembershipId,
                    amount = command.amount
                )
            }

            RemittanceType.BANK -> {
                bankingPort.requestFirmBanking(
                    bankName = command.toBankName!!,
                    bankAccountNumber = command.toBankAccountNumber!!,
                    amount = command.amount,
                )
            }

            else -> TODO()
        }

        entity.remittanceStatus = RemittanceStatus.SUCCESS
        requestRemittancePort.saveRemittanceRequestHistory(entity)
        return remittanceRequestMapper.mapToDomainEntity(entity)
    }
}