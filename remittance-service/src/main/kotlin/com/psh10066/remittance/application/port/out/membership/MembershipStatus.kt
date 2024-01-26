package com.psh10066.remittance.application.port.out.membership

data class MembershipStatus(
    val membershipId: Long,
    val isValid: Boolean
)