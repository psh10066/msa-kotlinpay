package com.psh10066.money.adapter.`in`.web

import com.psh10066.common.annotation.WebAdapter
import com.psh10066.money.application.port.`in`.CreateMemberMoneyCommand
import com.psh10066.money.application.port.`in`.CreateMemberMoneyUseCase
import com.psh10066.money.application.port.`in`.IncreaseMoneyRequestCommand
import com.psh10066.money.application.port.`in`.IncreaseMoneyRequestUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class RequestMoneyChangingController(
    private val increaseMoneyRequestUseCase: IncreaseMoneyRequestUseCase,
    private val createMemberMoneyUseCase: CreateMemberMoneyUseCase
) {

    @PostMapping(path = ["/money/increase"])
    fun increaseMoneyChangingRequest(@RequestBody request: IncreaseMoneyChangingRequest): MoneyChangingResultDetail {

        val command = IncreaseMoneyRequestCommand(
            targetMembershipId = request.targetMembershipId,
            amount = request.amount
        )

        increaseMoneyRequestUseCase.increaseMoneyRequest(command)
            .also {
                return MoneyChangingResultDetail(
                    moneyChangingRequestId = it.moneyChangingRequestId,
                    moneyChangingType = it.moneyChangingType,
                    moneyChangingResultStatus = MoneyChangingResultStatus.SUCCEEDED,
                    amount = it.moneyChangingAmount
                )
            }
    }

    @PostMapping(path = ["/money/increase-async"])
    fun increaseMoneyChangingRequestAsync(@RequestBody request: IncreaseMoneyChangingRequest): MoneyChangingResultDetail {

        val command = IncreaseMoneyRequestCommand(
            targetMembershipId = request.targetMembershipId,
            amount = request.amount
        )

        increaseMoneyRequestUseCase.increaseMoneyRequestAsync(command)
            .also {
                return MoneyChangingResultDetail(
                    moneyChangingRequestId = it.moneyChangingRequestId,
                    moneyChangingType = it.moneyChangingType,
                    moneyChangingResultStatus = MoneyChangingResultStatus.SUCCEEDED,
                    amount = it.moneyChangingAmount
                )
            }
    }

    @PostMapping(path = ["/money/create-member-money"])
    fun createMemberMoney(@RequestBody request: CreateMemberMoneyRequest) {
        createMemberMoneyUseCase.createMemberMoney(
            command = CreateMemberMoneyCommand(
                membershipId = request.membershipId
            )
        )
    }
}