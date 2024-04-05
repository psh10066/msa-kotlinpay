package com.psh10066.money.adapter.axon.aggregate

import com.psh10066.money.adapter.axon.command.MemberMoneyCreatedCommand
import com.psh10066.money.adapter.axon.event.MemberMoneyCreatedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import java.util.*

@Aggregate
data class MemberMoneyAggregate(

    @AggregateIdentifier
    var id: String? = null,

    var membershipId: Long? = 0,

    var balance: Int? = 0
) {
    @CommandHandler
    constructor(command: MemberMoneyCreatedCommand) : this() {
        println("MemberMoneyCreatedCommand Handler")
        AggregateLifecycle.apply(MemberMoneyCreatedEvent(membershipId = command.membershipId))
    }

    @EventSourcingHandler
    fun on(event: MemberMoneyCreatedEvent) {
        println("MemberMoneyCreatedEvent Sourcing Handler")
        id = UUID.randomUUID().toString()
        membershipId = event.membershipId
        balance = 0
    }
}