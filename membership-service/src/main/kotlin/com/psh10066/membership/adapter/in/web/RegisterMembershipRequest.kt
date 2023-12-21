package com.psh10066.membership.adapter.`in`.web

data class RegisterMembershipRequest(
    val name: String,
    val address: String,
    val email: String,
    val isCorp: Boolean
)