package com.psh10066.membership.application.port.`in`

import com.psh10066.membership.common.SelfValidating
import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.NotNull

data class ModifyMembershipCommand(

    @field:NotNull
    val membershipId: Long,

    @field:NotNull
    val name: String,

    @field:NotNull
    val email: String,

    @field:NotNull
    val address: String,

    @field:AssertTrue
    val isValid: Boolean,

    val isCorp: Boolean

) : SelfValidating<ModifyMembershipCommand>() {
    init {
        this.validateSelf()
    }
}
