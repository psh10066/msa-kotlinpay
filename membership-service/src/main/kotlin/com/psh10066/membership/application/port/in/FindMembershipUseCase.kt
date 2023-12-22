package com.psh10066.membership.application.port.`in`

import com.psh10066.membership.domain.Membership

interface FindMembershipUseCase {

    fun findMembership(command: FindMembershipCommand): Membership
}