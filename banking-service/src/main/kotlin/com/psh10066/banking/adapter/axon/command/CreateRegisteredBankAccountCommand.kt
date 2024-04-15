package com.psh10066.banking.adapter.axon.command

import com.psh10066.common.type.BankName

data class CreateRegisteredBankAccountCommand(

    val membershipId: Long? = null,

    val bankName: BankName? = null,
    val bankAccountNumber: String? = null
)