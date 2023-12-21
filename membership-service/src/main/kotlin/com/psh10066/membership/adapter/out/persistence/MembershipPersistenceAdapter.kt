package com.psh10066.membership.adapter.out.persistence

import com.psh10066.membership.application.port.out.FindMembershipPort
import com.psh10066.membership.application.port.out.RegisterMembershipPort
import com.psh10066.membership.common.PersistenceAdapter

@PersistenceAdapter
class MembershipPersistenceAdapter(
    val membershipRepository: SpringDataMembershipRepository
) : RegisterMembershipPort, FindMembershipPort {
    override fun createMembership(
        name: String,
        email: String,
        address: String,
        isValid: Boolean,
        isCorp: Boolean
    ): MembershipJpaEntity {
        return membershipRepository.save(
            MembershipJpaEntity(
                name = name,
                email = email,
                address = address,
                isValid = isValid,
                isCorp = isCorp
            )
        )
    }

    override fun findMembership(membershipId: Long): MembershipJpaEntity {
        return membershipRepository.getReferenceById(membershipId);
    }
}