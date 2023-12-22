package com.psh10066.membership.application.port.out

import com.psh10066.membership.adapter.out.persistence.MembershipJpaEntity

interface ModifyMembershipPort {

    fun modifyMembership(
        membershipId: Long,
        name: String,
        email: String,
        address: String,
        isValid: Boolean,
        isCorp: Boolean
    ): MembershipJpaEntity
}