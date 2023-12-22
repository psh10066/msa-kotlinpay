package com.psh10066.banking.domain

data class RegisteredBankAccount(
    val registeredBankAccountId: Long?,
    val membershipId: Long?,
    val bankName: String?,
    val bankAccountNumber: String?,
    val linkedStatusIsValid: Boolean?,
)