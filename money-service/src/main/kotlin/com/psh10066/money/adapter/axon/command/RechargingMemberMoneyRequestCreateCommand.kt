package com.psh10066.money.adapter.axon.command

import com.psh10066.common.SelfValidating
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class RechargingMemberMoneyRequestCreateCommand(

    @field:TargetAggregateIdentifier
    val aggregateIdentifier: String,

    val rechargingRequestId: String,

    val membershipId: Long,

    val amount: Long

) : SelfValidating<RechargingMemberMoneyRequestCreateCommand>() {
    init {
        this.validateSelf()
    }
}
