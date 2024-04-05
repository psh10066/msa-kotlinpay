package com.psh10066.money.adapter.axon.aggregate

import com.psh10066.money.adapter.axon.command.IncreaseMemberMoneyCommand
import com.psh10066.money.adapter.axon.command.MemberMoneyCreatedCommand
import com.psh10066.money.adapter.axon.event.IncreaseMemberMoneyEvent
import com.psh10066.money.adapter.axon.event.MemberMoneyCreatedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateCreationPolicy
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.CreationPolicy
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

    // https://discuss.axoniq.io/t/no-handler-registered-for-the-command/5338
    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    fun handle(command: IncreaseMemberMoneyCommand): String {
        println("IncreaseMemberMoneyCommand Handler")
        id = command.aggregateIdentifier
        AggregateLifecycle.apply(
            IncreaseMemberMoneyEvent(
                aggregateIdentifier = command.aggregateIdentifier,
                targetMembershipId = command.membershipId,
                amount = command.amount
            )
        )
        return command.aggregateIdentifier
    }

    @EventSourcingHandler
    fun on(event: IncreaseMemberMoneyEvent) {
        println("IncreaseMemberMoneyEvent Sourcing Handler")
        id = event.aggregateIdentifier
        membershipId = event.targetMembershipId
        balance = event.amount
    }
}