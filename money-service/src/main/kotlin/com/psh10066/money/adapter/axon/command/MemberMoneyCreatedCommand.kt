package com.psh10066.money.adapter.axon.command

import com.psh10066.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class MemberMoneyCreatedCommand(

    @field:NotNull
    val membershipId: Long

) : SelfValidating<MemberMoneyCreatedCommand>() {
    init {
        this.validateSelf()
    }
}
