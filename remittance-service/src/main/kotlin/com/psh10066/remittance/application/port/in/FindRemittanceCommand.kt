package com.psh10066.remittance.application.port.`in`

import com.psh10066.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class FindRemittanceCommand(

    @field:NotNull
    val membershipId: Long

) : SelfValidating<FindRemittanceCommand>() {
    init {
        this.validateSelf()
    }
}
