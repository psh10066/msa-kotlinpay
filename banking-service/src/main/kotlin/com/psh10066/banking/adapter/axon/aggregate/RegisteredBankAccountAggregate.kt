package com.psh10066.banking.adapter.axon.aggregate

import com.psh10066.banking.adapter.axon.command.CreateRegisteredBankAccountCommand
import com.psh10066.banking.adapter.axon.event.CreateRegisteredBankAccountEvent
import com.psh10066.common.type.BankName
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import java.util.*

@Aggregate
class RegisteredBankAccountAggregate(
    @AggregateIdentifier
    var id: String? = null,

    var membershipId: Long? = null,

    var bankName: BankName? = null,

    var bankAccountNumber: String? = null
) {

    @CommandHandler
    constructor(command: CreateRegisteredBankAccountCommand) : this() {
        println("CreateRegisteredBankAccountCommand Handler")

        AggregateLifecycle.apply(
            CreateRegisteredBankAccountEvent(
                membershipId = command.membershipId,
                bankName = command.bankName,
                bankAccountNumber = command.bankAccountNumber
            )
        )
    }

    @EventSourcingHandler
    fun on(event: CreateRegisteredBankAccountEvent) {
        println("CreateRegisteredBankAccountEvent Sourcing Handler")
        id = UUID.randomUUID().toString()
        membershipId = event.membershipId
        bankName = event.bankName
        bankAccountNumber = event.bankAccountNumber
    }
}