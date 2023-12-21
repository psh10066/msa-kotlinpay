package com.psh10066.membership.application.port.`in`

import com.fastcampuspay.membership.domain.Membership

interface FindMembershipUseCase {

    fun findMembership(command: FindMembershipCommand): Membership
}