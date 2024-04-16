package com.psh10066.banking.adapter.axon.event

import com.psh10066.common.type.BankName

data class CreateRegisteredBankAccountEvent(
    val membershipId: Long? = null,
    val bankName: BankName? = null,
    val bankAccountNumber: String? = null
)