package com.psh10066.banking.adapter.`in`.web

import com.psh10066.common.BankName

data class RegisterBankAccountRequest(
    val membershipId: Long,
    val bankName: BankName,
    val bankAccountNumber: String,
    val linkedStatusIsValid: Boolean
)