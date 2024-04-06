package com.psh10066.banking.application.port.out

import com.psh10066.banking.adapter.out.persistence.FirmBankingRequestJpaEntity
import com.psh10066.banking.adapter.out.persistence.FirmBankingStatus
import com.psh10066.common.type.BankName

interface RequestFirmBankingPort {

    fun createFirmBankingRequest(
        fromBankName: BankName,
        fromBankAccountNumber: String,
        toBankName: BankName,
        toBankAccountNumber: String,
        moneyAmount: Long,
        firmBankingStatus: FirmBankingStatus,
        aggregateIdentifier: String
    ): FirmBankingRequestJpaEntity

    fun modifyFirmBankingRequest(
        firmBankingRequestJpaEntity: FirmBankingRequestJpaEntity
    ): FirmBankingRequestJpaEntity

    fun getFirmBankingRequest(aggregateIdentifier: String): FirmBankingRequestJpaEntity
}