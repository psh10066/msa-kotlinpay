package com.psh10066.money.application.service

import com.psh10066.common.UseCase
import com.psh10066.money.adapter.out.persistence.MoneyChangingRequestMapper
import com.psh10066.money.application.port.`in`.IncreaseMoneyRequestCommand
import com.psh10066.money.application.port.`in`.IncreaseMoneyRequestUseCase
import com.psh10066.money.application.port.out.IncreaseMoneyPort
import com.psh10066.money.domain.MoneyChangingRequest
import com.psh10066.money.domain.MoneyChangingStatus
import com.psh10066.money.domain.MoneyChangingType
import org.springframework.transaction.annotation.Transactional

@UseCase
@Transactional
class IncreaseMoneyRequestService(
    private val increaseMoneyPort: IncreaseMoneyPort,
    private val moneyChangingRequestMapper: MoneyChangingRequestMapper
) : IncreaseMoneyRequestUseCase {
    override fun increaseMoneyRequest(command: IncreaseMoneyRequestCommand): MoneyChangingRequest {

        increaseMoneyPort.increaseMoney(
            membershipId = command.targetMembershipId,
            moneyAmount = command.amount
        )?.let {
            // 성공
            return moneyChangingRequestMapper.mapToDomainEntity(
                increaseMoneyPort.createMoneyChangingRequest(
                    targetMemberId = command.targetMembershipId,
                    moneyChangingType = MoneyChangingType.INCREASING,
                    changingMoneyAccount = command.amount,
                    moneyChangingStatus = MoneyChangingStatus.SUCCEEDED,
                )
            )
        }

        // 실패
        return moneyChangingRequestMapper.mapToDomainEntity(
            increaseMoneyPort.createMoneyChangingRequest(
                targetMemberId = command.targetMembershipId,
                moneyChangingType = MoneyChangingType.INCREASING,
                changingMoneyAccount = command.amount,
                moneyChangingStatus = MoneyChangingStatus.FAILED,
            )
        )
    }
}