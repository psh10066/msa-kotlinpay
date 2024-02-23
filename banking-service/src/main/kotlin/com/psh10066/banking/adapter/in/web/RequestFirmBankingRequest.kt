package com.psh10066.banking.adapter.`in`.web

import com.psh10066.common.type.BankName

data class RequestFirmBankingRequest(
    val fromBankName: BankName,
    val fromBankAccountNumber: String,

    val toBankName: BankName,
    val toBankAccountNumber: String,

    val moneyAmount: Long
)