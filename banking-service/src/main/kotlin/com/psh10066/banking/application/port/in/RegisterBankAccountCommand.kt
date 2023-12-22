package com.psh10066.banking.application.port.`in`

import com.psh10066.common.SelfValidating
import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.NotNull

data class RegisterBankAccountCommand(

    @field:NotNull
    val membershipId: Long,

    @field:NotNull
    val bankName: String,

    @field:NotNull
    val bankAccountNumber: String,

    @field:AssertTrue
    val linkedStatusIsValid: Boolean

) : SelfValidating<RegisterBankAccountCommand>() {
    init {
        this.validateSelf()
    }
}
