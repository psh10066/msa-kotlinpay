package com.psh10066.membership.adapter.out.persistence

import com.psh10066.membership.domain.Membership
import org.springframework.stereotype.Component

@Component
class MembershipMapper {

    fun mapToDomainEntity(membershipJpaEntity: MembershipJpaEntity): Membership {
        return Membership(
            membershipId = membershipJpaEntity.membershipId,
            name = membershipJpaEntity.name,
            email = membershipJpaEntity.email,
            address = membershipJpaEntity.address,
            isValid = membershipJpaEntity.isValid,
            isCorp = membershipJpaEntity.isCorp
        )
    }
}