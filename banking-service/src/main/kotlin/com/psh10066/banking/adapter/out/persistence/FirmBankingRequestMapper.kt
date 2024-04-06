package com.psh10066.banking.adapter.out.persistence

import com.psh10066.banking.domain.FirmBankingRequest
import org.springframework.stereotype.Component
import java.util.*

@Component
class FirmBankingRequestMapper {

    fun mapToDomainEntity(
        firmBankingRequestJpaEntity: FirmBankingRequestJpaEntity
    ): FirmBankingRequest {
        return FirmBankingRequest(
            firmBankingRequestId = firmBankingRequestJpaEntity.firmBankingRequestId,
            fromBankName = firmBankingRequestJpaEntity.fromBankName,
            fromBankAccountNumber = firmBankingRequestJpaEntity.fromBankAccountNumber,
            toBankName = firmBankingRequestJpaEntity.toBankName,
            toBankAccountNumber = firmBankingRequestJpaEntity.toBankAccountNumber,
            moneyAmount = firmBankingRequestJpaEntity.moneyAmount,
            firmBankingStatus = firmBankingRequestJpaEntity.firmBankingStatus,
            uuid = UUID.fromString(firmBankingRequestJpaEntity.uuid),
            aggregateIdentifier = firmBankingRequestJpaEntity.aggregateIdentifier
        )
    }
}