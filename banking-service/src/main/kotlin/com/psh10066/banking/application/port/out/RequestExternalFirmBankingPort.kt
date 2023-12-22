package com.psh10066.banking.application.port.out

import com.psh10066.banking.adapter.out.external.bank.ExternalFirmBankingRequest
import com.psh10066.banking.adapter.out.external.bank.FirmBankingResult

interface RequestExternalFirmBankingPort {

    fun requestExternalFirmBanking(request: ExternalFirmBankingRequest): FirmBankingResult
}