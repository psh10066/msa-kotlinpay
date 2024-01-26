package com.psh10066.remittance.adapter.out.persistence

import com.psh10066.remittance.domain.RemittanceRequest
import org.springframework.stereotype.Component

@Component
class RemittanceRequestMapper {

    fun mapToDomainEntity(remittanceRequestJpaEntity: RemittanceRequestJpaEntity): RemittanceRequest {
        return RemittanceRequest(
            remittanceRequestId = remittanceRequestJpaEntity.remittanceRequestId,
            remittanceFromMembershipId = remittanceRequestJpaEntity.fromMembershipId,
            toBankName = remittanceRequestJpaEntity.toBankName,
            toBankAccountNumber = remittanceRequestJpaEntity.toBankAccountNumber,
            remittanceType = remittanceRequestJpaEntity.remittanceType,
            amount = remittanceRequestJpaEntity.amount
        )
    }
}