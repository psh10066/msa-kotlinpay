package com.psh10066.money.adapter.out.service

import com.psh10066.common.type.BankName

data class RegisteredBankAccount(
    val registeredBankAccountId: String,
    val membershipId: Long,
    val bankName: BankName,
    val bankAccountNumber: String,
    val linkedStatusIsValid: Boolean,
    val aggregateIdentifier: String
)