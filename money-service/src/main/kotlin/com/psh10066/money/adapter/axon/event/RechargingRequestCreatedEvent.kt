package com.psh10066.money.adapter.axon.event

import com.psh10066.common.type.BankName

data class RechargingRequestCreatedEvent(
    val rechargingRequestId: String,
    val membershipId: Long,
    val amount: Long,
    val registeredBankAccountAggregateIdentifier: String,
    val bankName: BankName,
    val bankAccountNumber: String
)