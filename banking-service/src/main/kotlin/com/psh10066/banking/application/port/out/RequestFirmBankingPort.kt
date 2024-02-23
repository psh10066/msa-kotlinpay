package com.psh10066.banking.application.port.out

import com.psh10066.banking.adapter.out.persistence.FirmBankingRequestJpaEntity
import com.psh10066.banking.adapter.out.persistence.FirmBankingStatus

interface RequestFirmBankingPort {

    fun createFirmBankingRequest(
        fromBankName: String,
        fromBankAccountNumber: String,
        toBankName: String,
        toBankAccountNumber: String,
        moneyAmount: Long,
        firmBankingStatus: FirmBankingStatus
    ): FirmBankingRequestJpaEntity

    fun modifyFirmBankingRequest(
        firmBankingRequestJpaEntity: FirmBankingRequestJpaEntity
    ): FirmBankingRequestJpaEntity
}