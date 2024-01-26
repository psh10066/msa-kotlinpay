package com.psh10066.remittance.application.port.out.membership

interface MembershipPort {

    fun getMembershipStatus(membershipId: Long): MembershipStatus?
}