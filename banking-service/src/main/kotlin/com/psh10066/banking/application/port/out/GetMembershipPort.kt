package com.psh10066.banking.application.port.out

import com.psh10066.banking.adapter.out.service.MembershipStatus

interface GetMembershipPort {

    fun getMembership(membershipId: Long): MembershipStatus?
}