package com.psh10066.remittance.adapter.`in`.web

import com.psh10066.common.WebAdapter
import com.psh10066.remittance.application.port.`in`.RequestRemittanceCommand
import com.psh10066.remittance.application.port.`in`.RequestRemittanceUseCase
import com.psh10066.remittance.domain.RemittanceRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class RequestRemittanceController(
    private val requestRemittanceUseCase: RequestRemittanceUseCase
) {

    @PostMapping(path = ["/remittance/request"])
    fun requestRemittance(@RequestBody request: RequestRemittanceRequest): RemittanceRequest? {
        val command = RequestRemittanceCommand(
            fromMembershipId = request.fromMembershipId,
            toMembershipId = request.toMembershipId,
            toBankName = request.toBankName,
            toBankAccountNumber = request.toBankAccountNumber,
            remittanceType = request.remittanceType,
            amount = request.amount
        )

        return requestRemittanceUseCase.requestRemittance(command)
    }
}