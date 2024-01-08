package com.psh10066.money.application.port.`in`

import com.psh10066.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class IncreaseMoneyRequestCommand(

    @field:NotNull
    val targetMembershipId: Long,

    @field:NotNull
    val amount: Int,

) : SelfValidating<IncreaseMoneyRequestCommand>() {
    init {
        this.validateSelf()
    }
}
