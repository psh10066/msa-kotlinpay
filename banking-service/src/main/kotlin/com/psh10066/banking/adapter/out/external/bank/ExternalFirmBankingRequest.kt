package com.psh10066.banking.adapter.out.external.bank

import com.psh10066.common.type.BankName

data class ExternalFirmBankingRequest(
    val fromBankName: BankName,
    val fromBankAccountNumber: String,

    val toBankName: BankName,
    val toBankAccountNumber: String,

    val amount: Long
)