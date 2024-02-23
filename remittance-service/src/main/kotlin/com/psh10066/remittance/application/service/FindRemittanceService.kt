package com.psh10066.remittance.application.service

import com.psh10066.common.annotation.UseCase
import com.psh10066.remittance.adapter.out.persistence.RemittanceRequestMapper
import com.psh10066.remittance.application.port.`in`.FindRemittanceCommand
import com.psh10066.remittance.application.port.`in`.FindRemittanceUseCase
import com.psh10066.remittance.application.port.out.FindRemittancePort
import com.psh10066.remittance.domain.RemittanceRequest
import org.springframework.transaction.annotation.Transactional

@UseCase
@Transactional
class FindRemittanceService(
    private val findRemittancePort: FindRemittancePort,
    private val remittanceRequestMapper: RemittanceRequestMapper,
) : FindRemittanceUseCase {
    override fun findRemittanceHistory(command: FindRemittanceCommand): List<RemittanceRequest> {
        return findRemittancePort.findRemittanceHistory(command).map { remittanceRequestMapper.mapToDomainEntity(it) }
    }
}