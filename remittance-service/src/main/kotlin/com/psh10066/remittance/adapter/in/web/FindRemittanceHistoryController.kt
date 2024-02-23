package com.psh10066.remittance.adapter.`in`.web

import com.psh10066.common.annotation.WebAdapter
import com.psh10066.remittance.application.port.`in`.FindRemittanceCommand
import com.psh10066.remittance.application.port.`in`.FindRemittanceUseCase
import com.psh10066.remittance.domain.RemittanceRequest
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class FindRemittanceHistoryController(
    private val findRemittanceUseCase: FindRemittanceUseCase
) {

    @PostMapping(path = ["/remittance/{membershipId}"])
    fun findRemittanceHistory(@PathVariable membershipId: Long): List<RemittanceRequest> {
        val command = FindRemittanceCommand(
            membershipId = membershipId
        )

        return findRemittanceUseCase.findRemittanceHistory(command)
    }
}