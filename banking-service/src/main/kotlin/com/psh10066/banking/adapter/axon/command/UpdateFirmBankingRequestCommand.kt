package com.psh10066.banking.adapter.axon.command

import com.psh10066.banking.adapter.out.persistence.FirmBankingStatus
import jakarta.validation.constraints.NotNull
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class UpdateFirmBankingRequestCommand(

    @field:NotNull
    @field:TargetAggregateIdentifier
    val aggregateIdentifier: String,

    val firmBankingStatus: FirmBankingStatus
)