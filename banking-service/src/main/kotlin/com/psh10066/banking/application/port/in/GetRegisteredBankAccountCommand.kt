package com.psh10066.banking.application.port.`in`

import com.psh10066.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class GetRegisteredBankAccountCommand(

    @field:NotNull
    val membershipId: Long

) : SelfValidating<GetRegisteredBankAccountCommand>() {
    init {
        this.validateSelf()
    }
}
