package com.psh10066.remittance.application.port.`in`

import com.psh10066.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class RequestRemittanceCommand(

    @field:NotNull
    val fromMembershipId: Long?,

    val toMembershipId: Long?,

    val toBankName: String?,
    val toBankAccountNumber: String?,

    @field:NotNull
    val remittanceType: RemittanceType?,

    @field:NotNull
    val amount: Int?

) : SelfValidating<RequestRemittanceCommand>() {
    init {
        this.validateSelf()
    }
}

