package com.psh10066.money.adapter.out.persistence

import com.psh10066.money.domain.MoneyChangingRequest
import org.springframework.stereotype.Component

@Component
class MoneyChangingRequestMapper {

    fun mapToDomainEntity(moneyChangingRequestJpaEntity: MoneyChangingRequestJpaEntity) =
        MoneyChangingRequest(
            moneyChangingRequestId = moneyChangingRequestJpaEntity.moneyChangingRequestId,
            targetMembershipId = moneyChangingRequestJpaEntity.targetMembershipId,
            moneyChangingType = moneyChangingRequestJpaEntity.moneyChangingType,
            moneyChangingAmount = moneyChangingRequestJpaEntity.moneyAmount,
            moneyChangingStatus = moneyChangingRequestJpaEntity.moneyChangingStatus,
            uuid = moneyChangingRequestJpaEntity.uuid,
        )
}