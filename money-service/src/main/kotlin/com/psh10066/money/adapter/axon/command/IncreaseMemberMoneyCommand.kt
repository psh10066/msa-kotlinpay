package com.psh10066.money.adapter.axon.command

import com.psh10066.common.SelfValidating
import jakarta.validation.constraints.NotNull
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class IncreaseMemberMoneyCommand(

    @field:NotNull
    @field:TargetAggregateIdentifier
    val aggregateIdentifier: String,

    @field:NotNull
    val membershipId: Long,

    @field:NotNull
    val amount: Int

) : SelfValidating<IncreaseMemberMoneyCommand>() {
    init {
        this.validateSelf()
    }
}
