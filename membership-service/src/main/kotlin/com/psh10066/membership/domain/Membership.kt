package com.psh10066.membership.domain

data class Membership(
    val membershipId: Long?,
    val name: String?,
    val email: String?,
    val address: String?,
    val isValid: Boolean?,
    val isCorp: Boolean?
)