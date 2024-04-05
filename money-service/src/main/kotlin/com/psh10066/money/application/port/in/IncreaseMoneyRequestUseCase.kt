package com.psh10066.money.application.port.`in`

import com.psh10066.money.domain.MoneyChangingRequest

interface IncreaseMoneyRequestUseCase {

    fun increaseMoneyRequest(command: IncreaseMoneyRequestCommand): MoneyChangingRequest
    fun increaseMoneyRequestAsync(command: IncreaseMoneyRequestCommand): MoneyChangingRequest

    fun increaseMoneyRequestByEvent(command: IncreaseMoneyRequestCommand)
}