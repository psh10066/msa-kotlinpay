package com.psh10066.remittance.application.port.`in`

import com.psh10066.remittance.domain.RemittanceRequest

interface FindRemittanceUseCase {

    fun findRemittanceHistory(command: FindRemittanceCommand): List<RemittanceRequest>
}