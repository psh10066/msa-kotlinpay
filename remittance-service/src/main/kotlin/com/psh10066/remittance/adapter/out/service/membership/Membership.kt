package com.psh10066.remittance.adapter.out.service.membership

data class Membership(
    val membershipId: Long,
    val name: String?,
    val email: String?,
    val address: String?,
    val isValid: Boolean,
    val isCorp: Boolean?
)