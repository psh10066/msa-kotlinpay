package com.psh10066.banking.adapter.out.persistence

import com.psh10066.banking.application.port.out.RequestFirmBankingPort
import com.psh10066.common.type.BankName
import com.psh10066.common.annotation.PersistenceAdapter
import java.util.*

@PersistenceAdapter
class FirmBankingRequestPersistenceAdapter(
    val firmBankingRequestRepository: SpringDataFirmBankingRequestRepository
) : RequestFirmBankingPort {
    override fun createFirmBankingRequest(
        fromBankName: BankName,
        fromBankAccountNumber: String,
        toBankName: BankName,
        toBankAccountNumber: String,
        moneyAmount: Long,
        firmBankingStatus: FirmBankingStatus,
        aggregateIdentifier: String
    ): FirmBankingRequestJpaEntity {
        return firmBankingRequestRepository.save(
            FirmBankingRequestJpaEntity(
                fromBankName = fromBankName,
                fromBankAccountNumber = fromBankAccountNumber,
                toBankName = toBankName,
                toBankAccountNumber = toBankAccountNumber,
                moneyAmount = moneyAmount,
                firmBankingStatus = firmBankingStatus,
                uuid = UUID.randomUUID().toString(),
                aggregateIdentifier = aggregateIdentifier
            )
        )
    }

    override fun modifyFirmBankingRequest(firmBankingRequestJpaEntity: FirmBankingRequestJpaEntity): FirmBankingRequestJpaEntity {
        firmBankingRequestJpaEntity.uuid = UUID.randomUUID().toString()
        return firmBankingRequestRepository.save(firmBankingRequestJpaEntity)
    }

    override fun getFirmBankingRequest(aggregateIdentifier: String): FirmBankingRequestJpaEntity {
        return firmBankingRequestRepository.findByAggregateIdentifier(aggregateIdentifier)
    }

}