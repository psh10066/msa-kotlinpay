package com.psh10066.remittance.application.port.out.banking

import com.psh10066.common.BankName

data class BankingInfo(
    val bankName: BankName?,
    val bankAccountNumber: String?,
    val isValid: Boolean?
)