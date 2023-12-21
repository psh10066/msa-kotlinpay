package com.psh10066.membership.application.port.out

import com.psh10066.membership.adapter.out.persistence.MembershipJpaEntity

interface RegisterMembershipPort {

    fun createMembership(
        name: String,
        email: String,
        address: String,
        isValid: Boolean,
        isCorp: Boolean
    ): MembershipJpaEntity
}