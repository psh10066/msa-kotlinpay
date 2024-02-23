package com.psh10066.remittance.application.service

import com.psh10066.common.UseCase
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
            return null
        }

        val moneyInfo = moneyPort.getMoneyInfo(command.fromMembershipId)
        if (moneyInfo.balance < command.amount!!) {
            val rechargeAmount = (ceil(command.amount - moneyInfo.balance / 10000.0) * 10000).toInt()
            val moneyResult = moneyPort.requestMoneyRecharging(command.fromMembershipId, rechargeAmount)
            if (!moneyResult) {
                return null
            }
        }

        when (command.remittanceType) {
            RemittanceType.MEMBERSHIP -> {
                val remittanceResult1 = moneyPort.requestMoneyDecrease(
                    membershipId = command.fromMembershipId,
                    amount = command.amount
                )
                val remittanceResult2 = moneyPort.requestMoneyIncrease(
                    membershipId = command.toMembershipId,
                    amount = command.amount
                )
                if (!remittanceResult1 || !remittanceResult2) {
                    return null
                }
            }

            RemittanceType.BANK -> {
                val remittanceRequest = bankingPort.requestFirmBanking(
                    bankName = command.toBankName!!,
                    bankAccountNumber = command.toBankAccountNumber!!,
                    amount = command.amount,
                )
                if (!remittanceRequest) {
                    return null
                }
            }

            else -> TODO()
        }

        entity.remittanceStatus = RemittanceStatus.SUCCESS
        val result = requestRemittancePort.saveRemittanceRequestHistory(entity)
        if (result) {
            return remittanceRequestMapper.mapToDomainEntity(entity)
        }
        return null
    }
}