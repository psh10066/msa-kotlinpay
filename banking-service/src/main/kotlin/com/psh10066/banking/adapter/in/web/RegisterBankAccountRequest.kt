package com.psh10066.banking.adapter.`in`.web

data class RegisterBankAccountRequest(
    val membershipId: Long,
    val bankName: String,
    val bankAccountNumber: String,
    val linkedStatusIsValid: Boolean
)