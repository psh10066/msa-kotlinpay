package com.psh10066.membership.application.port.out

import com.psh10066.membership.adapter.out.persistence.MembershipJpaEntity

interface FindMembershipPort {

    fun findMembership(
        membershipId: Long
    ): MembershipJpaEntity
}