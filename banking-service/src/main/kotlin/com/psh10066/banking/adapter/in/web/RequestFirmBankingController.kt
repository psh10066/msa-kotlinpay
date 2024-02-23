package com.psh10066.banking.adapter.`in`.web

import com.psh10066.banking.application.port.`in`.RequestFirmBankingCommand
import com.psh10066.banking.application.port.`in`.RequestFirmBankingUseCase
import com.psh10066.banking.domain.FirmBankingRequest
import com.psh10066.common.annotation.WebAdapter
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class RequestFirmBankingController(
    private val requestFirmBankingUseCase: RequestFirmBankingUseCase
) {

    @PostMapping(path = ["/banking/firm-banking/request"])
    fun requestFirmBanking(@RequestBody request: RequestFirmBankingRequest): FirmBankingRequest {

        val command = RequestFirmBankingCommand(
            fromBankName = request.fromBankName,
            fromBankAccountNumber = request.fromBankAccountNumber,
            toBankName = request.toBankName,
            toBankAccountNumber = request.toBankAccountNumber,
            moneyAmount = request.moneyAmount
        )

        return requestFirmBankingUseCase.requestFirmBanking(command)
    }
}