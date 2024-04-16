package com.psh10066.common.event

import com.psh10066.common.type.BankName
import org.axonframework.modelling.command.TargetAggregateIdentifier

class RequestFirmBankingCommand(
    val requestFirmBankingId: String,

    @TargetAggregateIdentifier
    val aggregateIdentifier: String,

    val rechargeRequestId: String,

    val membershipId: Long,

    val fromBankName: BankName,

    val fromBankAccountNumber: String,

    val toBankName: BankName,

    val toBankAccountNumber: String,

    val moneyAmount: Long
)