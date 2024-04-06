package com.psh10066.banking.domain

import com.psh10066.banking.adapter.out.persistence.FirmBankingStatus
import com.psh10066.common.type.BankName
import java.util.*

data class FirmBankingRequest(
    val firmBankingRequestId: Long?,
    val fromBankName: BankName?,
    val fromBankAccountNumber: String?,
    val toBankName: BankName?,
    val toBankAccountNumber: String?,
    val moneyAmount: Long?,
    val firmBankingStatus: FirmBankingStatus = FirmBankingStatus.REQUESTED,
    val uuid: UUID?,
    val aggregateIdentifier: String? = null
)