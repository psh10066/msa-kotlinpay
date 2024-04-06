package com.psh10066.banking.adapter.axon.aggregate

import com.psh10066.banking.adapter.axon.command.CreateFirmBankingRequestCommand
import com.psh10066.banking.adapter.axon.command.UpdateFirmBankingRequestCommand
import com.psh10066.banking.adapter.axon.event.FirmBankingRequestCreatedEvent
import com.psh10066.banking.adapter.axon.event.FirmBankingRequestUpdatedEvent
import com.psh10066.banking.adapter.out.persistence.FirmBankingStatus
import com.psh10066.common.type.BankName
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateCreationPolicy
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.CreationPolicy
import org.axonframework.spring.stereotype.Aggregate
import java.util.*

@Aggregate
data class FirmBankingRequestAggregate(

    @AggregateIdentifier
    var id: String? = null,

    var fromBankName: BankName? = null,
    var fromBankAccountNumber: String? = null,

    var toBankName: BankName? = null,
    var toBankAccountNumber: String? = null,

    var moneyAmount: Long? = 0,
    var firmBankingStatus: FirmBankingStatus? = null
) {

    @CommandHandler
    constructor(command: CreateFirmBankingRequestCommand) : this() {
        println("CreateFirmBankingRequestCommand Handler")

        AggregateLifecycle.apply(
            FirmBankingRequestCreatedEvent(
                fromBankName = command.fromBankName,
                fromBankAccountNumber = command.fromBankAccountNumber,
                toBankName = command.toBankName,
                toBankAccountNumber = command.toBankAccountNumber,
                moneyAmount = command.moneyAmount
            )
        )
    }

    @EventSourcingHandler
    fun on(event: FirmBankingRequestCreatedEvent) {
        println("FirmBankingRequestCreatedEvent Sourcing Handler")
        id = UUID.randomUUID().toString()
        fromBankName = event.fromBankName
        fromBankAccountNumber = event.fromBankAccountNumber
        toBankName = event.toBankName
        toBankAccountNumber = event.toBankAccountNumber
    }

    // https://discuss.axoniq.io/t/no-handler-registered-for-the-command/5338
    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    fun handle(command: UpdateFirmBankingRequestCommand): String {
        println("UpdateFirmBankingRequestCommand Handler")

        id = command.aggregateIdentifier
        AggregateLifecycle.apply(
            FirmBankingRequestUpdatedEvent(
                firmBankingStatus = command.firmBankingStatus
            )
        )
        return command.aggregateIdentifier
    }

    @EventSourcingHandler
    fun on(event: FirmBankingRequestUpdatedEvent) {
        println("FirmBankingRequestUpdatedEvent Sourcing Handler")
        firmBankingStatus = event.firmBankingStatus
    }
}