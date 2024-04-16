package com.psh10066.common.event

import com.psh10066.common.type.BankName
import org.axonframework.modelling.command.TargetAggregateIdentifier

class CheckRegisteredBankAccountCommand(
    @TargetAggregateIdentifier
    val aggregateIdentifier: String,

    val rechargeRequestId: String,

    val membershipId: Long,

    val checkRegisteredBankAccountId: String,

    val bankName: BankName,

    val bankAccountNumber: String,

    val amount: Long
)