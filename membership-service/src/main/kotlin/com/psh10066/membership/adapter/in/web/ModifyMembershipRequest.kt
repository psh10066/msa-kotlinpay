package com.psh10066.membership.adapter.`in`.web

data class ModifyMembershipRequest(
    val membershipId: Long,
    val name: String,
    val address: String,
    val email: String,
    val isValid: Boolean,
    val isCorp: Boolean
)