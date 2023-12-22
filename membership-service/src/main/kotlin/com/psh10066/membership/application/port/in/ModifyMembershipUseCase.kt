package com.psh10066.membership.application.port.`in`

import com.fastcampuspay.membership.domain.Membership

interface ModifyMembershipUseCase {

    fun modifyMembership(command: ModifyMembershipCommand): Membership
}