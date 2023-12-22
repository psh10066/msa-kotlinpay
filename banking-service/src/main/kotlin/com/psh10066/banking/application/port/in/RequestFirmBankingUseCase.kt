package com.psh10066.banking.application.port.`in`

import com.psh10066.banking.domain.FirmBankingRequest

interface RequestFirmBankingUseCase {

    fun requestFirmBanking(command: RequestFirmBankingCommand): FirmBankingRequest
}