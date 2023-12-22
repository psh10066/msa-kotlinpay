package com.psh10066.banking.adapter.out.persistence

import com.psh10066.banking.domain.FirmBankingRequest
import org.springframework.stereotype.Component
import java.util.*

@Component
class FirmBankingRequestMapper {

    fun mapToDomainEntity(
        registeredBankAccountJpaEntity: FirmBankingRequestJpaEntity
    ): FirmBankingRequest {
        return FirmBankingRequest(
            firmBankingRequestId = registeredBankAccountJpaEntity.firmBankingRequestId,
            fromBankName = registeredBankAccountJpaEntity.fromBankName,
            fromBankAccountNumber = registeredBankAccountJpaEntity.fromBankAccountNumber,
            toBankName = registeredBankAccountJpaEntity.toBankName,
            toBankAccountNumber = registeredBankAccountJpaEntity.toBankAccountNumber,
            moneyAmount = registeredBankAccountJpaEntity.moneyAmount,
            firmBankingStatus = registeredBankAccountJpaEntity.firmBankingStatus,
            uuid = UUID.fromString(registeredBankAccountJpaEntity.uuid)
        )
    }
}