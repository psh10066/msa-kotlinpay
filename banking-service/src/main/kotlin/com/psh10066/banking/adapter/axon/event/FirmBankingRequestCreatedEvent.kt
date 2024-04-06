package com.psh10066.banking.adapter.axon.event

import com.psh10066.common.type.BankName

data class FirmBankingRequestCreatedEvent(

    val fromBankName: BankName? = null,
    val fromBankAccountNumber: String? = null,

    val toBankName: BankName? = null,
    val toBankAccountNumber: String? = null,

    val moneyAmount: Long? = 0
)