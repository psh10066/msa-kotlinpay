package com.psh10066.remittance.adapter.out.persistence

import com.psh10066.common.PersistenceAdapter
import com.psh10066.remittance.application.port.`in`.FindRemittanceCommand
import com.psh10066.remittance.application.port.`in`.RequestRemittanceCommand
import com.psh10066.remittance.application.port.out.FindRemittancePort
import com.psh10066.remittance.application.port.out.RequestRemittancePort

@PersistenceAdapter
class RemittanceRequestPersistenceAdapter(
    private val remittanceRequestRepository: SpringDataRemittanceRequestRepository
) : RequestRemittancePort, FindRemittancePort {
    override fun createRemittanceRequestHistory(command: RequestRemittanceCommand): RemittanceRequestJpaEntity {
        return remittanceRequestRepository.save(
            RemittanceRequestJpaEntity(
                fromMembershipId = command.fromMembershipId,
                toMembershipId = command.toMembershipId,
                toBankName = command.toBankName,
                toBankAccountNumber = command.toBankAccountNumber,
                amount = command.amount,
                remittanceType = command.remittanceType
            )
        )
    }

    override fun saveRemittanceRequestHistory(remittanceRequestJpaEntity: RemittanceRequestJpaEntity): Boolean {
        remittanceRequestRepository.save(remittanceRequestJpaEntity)
        return true
    }

    override fun findRemittanceHistory(command: FindRemittanceCommand): List<RemittanceRequestJpaEntity> {
        return remittanceRequestRepository.findAll()
    }
}