package com.psh10066.banking.adapter.`in`.web

data class RequestFirmBankingRequest(
    val fromBankName: String,
    val fromBankAccountNumber: String,

    val toBankName: String,
    val toBankAccountNumber: String,

    val moneyAmount: Long
)