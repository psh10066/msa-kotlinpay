package com.psh10066.banking.application.port.out

import com.fasterxml.jackson.annotation.JsonProperty

data class Membership(
    val membershipId: Long?,
    val name: String?,
    val email: String?,
    val address: String?,

    @get:JsonProperty("valid")
    val isValid: Boolean?,
    @get:JsonProperty("corp")
    val isCorp: Boolean?
)