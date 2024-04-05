package com.psh10066.money.adapter.axon.event

import com.psh10066.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class MemberMoneyCreatedEvent(

    @field:NotNull
    val membershipId: Long

) : SelfValidating<MemberMoneyCreatedEvent>() {
    init {
        this.validateSelf()
    }
}
