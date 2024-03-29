package com.psh10066.membership.application.port.`in`

import com.psh10066.common.SelfValidating
import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.NotNull

data class RegisterMembershipCommand(

    @field:NotNull
    val name: String,

    @field:NotNull
    val email: String,

    @field:NotNull
    val address: String,

    @field:AssertTrue
    val isValid: Boolean,

    val isCorp: Boolean

) : SelfValidating<RegisterMembershipCommand>() {
    init {
        this.validateSelf()
    }
}

