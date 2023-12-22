package com.psh10066.membership.adapter.out.persistence

import com.psh10066.common.PersistenceAdapter
import com.psh10066.membership.application.port.out.FindMembershipPort
import com.psh10066.membership.application.port.out.ModifyMembershipPort
import com.psh10066.membership.application.port.out.RegisterMembershipPort

@PersistenceAdapter
class MembershipPersistenceAdapter(
    val membershipRepository: SpringDataMembershipRepository
) : RegisterMembershipPort, FindMembershipPort, ModifyMembershipPort {
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

    override fun modifyMembership(
        membershipId: Long,
        name: String,
        email: String,
        address: String,
        isValid: Boolean,
        isCorp: Boolean
    ): MembershipJpaEntity {
        val jpaEntity = membershipRepository.getReferenceById(membershipId);

        jpaEntity.name = name
        jpaEntity.email = email
        jpaEntity.address = address
        jpaEntity.isValid = isValid
        jpaEntity.isCorp = isCorp

        return membershipRepository.save(jpaEntity)
    }

}