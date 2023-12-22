package com.psh10066.banking.application.port.`in`

import com.psh10066.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class RequestFirmBankingCommand(

    @field:NotNull
    val fromBankName: String,

    @field:NotNull
    val fromBankAccountNumber: String,

    @field:NotNull
    val toBankName: String,

    @field:NotNull
    val toBankAccountNumber: String,

    @field:NotNull
    val moneyAmount: Long

) : SelfValidating<RequestFirmBankingCommand>() {
    init {
        this.validateSelf()
    }
}
