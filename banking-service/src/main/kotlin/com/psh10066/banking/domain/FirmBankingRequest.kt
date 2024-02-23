package com.psh10066.banking.domain

import com.psh10066.banking.adapter.out.persistence.FirmBankingStatus
import java.util.*

data class FirmBankingRequest(
    val firmBankingRequestId: Long?,
    val fromBankName: String?,
    val fromBankAccountNumber: String?,
    val toBankName: String?,
    val toBankAccountNumber: String?,
    val moneyAmount: Long?,
    val firmBankingStatus: FirmBankingStatus = FirmBankingStatus.REQUESTED,
    val uuid: UUID?
)