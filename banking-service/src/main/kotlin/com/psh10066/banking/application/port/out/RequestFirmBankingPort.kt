package com.psh10066.banking.application.port.out

import com.psh10066.banking.adapter.out.persistence.FirmBankingRequestJpaEntity

interface RequestFirmBankingPort {

    fun createFirmBankingRequest(
        fromBankName: String,
        fromBankAccountNumber: String,
        toBankName: String,
        toBankAccountNumber: String,
        moneyAmount: Long,
        firmBankingStatus: Int
    ): FirmBankingRequestJpaEntity

    fun modifyFirmBankingRequest(
        firmBankingRequestJpaEntity: FirmBankingRequestJpaEntity
    ): FirmBankingRequestJpaEntity
}