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
    val remittanceType: Int?, // 0: membership (내부 고객), 1: bank (외부 은행 계좌)

    @field:NotNull
    val amount: Int?

) : SelfValidating<RequestRemittanceCommand>() {
    init {
        this.validateSelf()
    }
}

