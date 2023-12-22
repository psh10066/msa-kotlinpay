package com.psh10066.banking.domain

import java.util.*

data class FirmBankingRequest(
    val firmBankingRequestId: Long?,
    val fromBankName: String?,
    val fromBankAccountNumber: String?,
    val toBankName: String?,
    val toBankAccountNumber: String?,
    val moneyAmount: Long?,
    val firmBankingStatus: Int = 0,
    val uuid: UUID?
)