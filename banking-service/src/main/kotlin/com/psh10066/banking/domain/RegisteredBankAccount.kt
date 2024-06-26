package com.psh10066.banking.domain

import com.psh10066.common.type.BankName

data class RegisteredBankAccount(
    val registeredBankAccountId: Long?,
    val membershipId: Long?,
    val bankName: BankName?,
    val bankAccountNumber: String?,
    val linkedStatusIsValid: Boolean?,
    val aggregateIdentifier: String?
)