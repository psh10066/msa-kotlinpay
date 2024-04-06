package com.psh10066.banking.adapter.axon.command

import com.psh10066.common.type.BankName

data class CreateFirmBankingRequestCommand(

    val fromBankName: BankName? = null,
    val fromBankAccountNumber: String? = null,

    val toBankName: BankName? = null,
    val toBankAccountNumber: String? = null,

    val moneyAmount: Long? = 0
)