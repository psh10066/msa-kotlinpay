package com.psh10066.banking.application.port.`in`

import com.psh10066.common.type.BankName
import com.psh10066.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class RequestFirmBankingCommand(

    @field:NotNull
    val fromBankName: BankName,

    @field:NotNull
    val fromBankAccountNumber: String,

    @field:NotNull
    val toBankName: BankName,

    @field:NotNull
    val toBankAccountNumber: String,

    @field:NotNull
    val moneyAmount: Long

) : SelfValidating<RequestFirmBankingCommand>() {
    init {
        this.validateSelf()
    }
}
