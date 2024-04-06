package com.psh10066.banking.adapter.`in`.web

import com.psh10066.banking.adapter.out.persistence.FirmBankingStatus

data class UpdateFirmBankingRequest(

    val firmBankingRequestAggregateIdentifier: String,
    val firmBankingStatus: FirmBankingStatus
)