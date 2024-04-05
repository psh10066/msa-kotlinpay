package com.psh10066.money.application.port.`in`

import com.psh10066.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class CreateMemberMoneyCommand(

    @field:NotNull
    val membershipId: Long

) : SelfValidating<CreateMemberMoneyCommand>() {
    init {
        this.validateSelf()
    }
}
