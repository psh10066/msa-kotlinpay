package com.psh10066.membership.application.port.`in`

import com.psh10066.membership.domain.Membership

interface ModifyMembershipUseCase {

    fun modifyMembership(command: ModifyMembershipCommand): Membership
}