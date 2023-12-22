package com.psh10066.membership.application.port.`in`

import com.psh10066.membership.domain.Membership

interface RegisterMembershipUseCase {

    fun registerMembership(command: RegisterMembershipCommand): Membership
}