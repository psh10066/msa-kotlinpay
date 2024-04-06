package com.psh10066.banking.application.port.`in`

import com.psh10066.banking.adapter.out.persistence.FirmBankingStatus
import com.psh10066.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class UpdateFirmBankingCommand(

    @field:NotNull
    val firmBankingAggregateIdentifier: String,

    @field:NotNull
    val firmBankingStatus: FirmBankingStatus

) : SelfValidating<UpdateFirmBankingCommand>() {
    init {
        this.validateSelf()
    }
}
