package com.psh10066.banking.adapter.axon.event

import com.psh10066.banking.adapter.out.persistence.FirmBankingStatus

data class FirmBankingRequestUpdatedEvent(

    val firmBankingStatus: FirmBankingStatus
)