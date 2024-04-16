package com.psh10066.money.application.port.out

import com.psh10066.common.type.BankName

data class RegisteredBankAccountAggregateIdentifier(
    val registeredBankAccountId: String,
    val aggregateIdentifier: String,
    val membershipId: Long,
    val bankName: BankName,
    val bankAccountNumber: String
)