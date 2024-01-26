package com.psh10066.remittance.application.port.out

import com.psh10066.remittance.adapter.out.persistence.RemittanceRequestJpaEntity
import com.psh10066.remittance.application.port.`in`.FindRemittanceCommand

interface FindRemittancePort {

    fun findRemittanceHistory(command: FindRemittanceCommand): List<RemittanceRequestJpaEntity>
}