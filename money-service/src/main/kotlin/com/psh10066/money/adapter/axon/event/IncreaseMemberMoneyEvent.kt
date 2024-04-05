package com.psh10066.money.adapter.axon.event

import com.psh10066.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class IncreaseMemberMoneyEvent(

    @field:NotNull
    val aggregateIdentifier: String,

    @field:NotNull
    val targetMembershipId: Long,

    @field:NotNull
    val amount: Int

) : SelfValidating<IncreaseMemberMoneyEvent>() {
    init {
        this.validateSelf()
    }
}
