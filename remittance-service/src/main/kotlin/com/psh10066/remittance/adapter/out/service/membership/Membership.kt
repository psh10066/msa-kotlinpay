package com.psh10066.remittance.adapter.out.service.membership

import com.fasterxml.jackson.annotation.JsonProperty

data class Membership(
    val membershipId: Long,
    val name: String?,
    val email: String?,
    val address: String?,

    @get:JsonProperty("valid")
    val isValid: Boolean,
    @get:JsonProperty("corp")
    val isCorp: Boolean?
)