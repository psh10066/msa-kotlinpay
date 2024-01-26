package com.psh10066.remittance.application.port.out.banking

data class BankingInfo(
    val bankName: String?,
    val bankAccountNumber: String?,
    val isValid: Boolean?
)