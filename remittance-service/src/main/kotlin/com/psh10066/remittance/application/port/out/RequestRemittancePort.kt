package com.psh10066.remittance.application.port.out

import com.psh10066.remittance.adapter.out.persistence.RemittanceRequestJpaEntity
import com.psh10066.remittance.application.port.`in`.RequestRemittanceCommand

interface RequestRemittancePort {

    fun createRemittanceRequestHistory(command: RequestRemittanceCommand): RemittanceRequestJpaEntity
    fun saveRemittanceRequestHistory(remittanceRequestJpaEntity: RemittanceRequestJpaEntity): Boolean
}