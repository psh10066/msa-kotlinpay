package com.psh10066.remittance.application.port.`in`

import com.psh10066.remittance.domain.RemittanceRequest

interface RequestRemittanceUseCase {

    fun requestRemittance(command: RequestRemittanceCommand): RemittanceRequest?
}