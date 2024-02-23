package com.psh10066.banking.application.service

import com.psh10066.banking.adapter.out.external.bank.ExternalFirmBankingRequest
import com.psh10066.banking.adapter.out.persistence.FirmBankingRequestMapper
import com.psh10066.banking.adapter.out.persistence.FirmBankingStatus
import com.psh10066.banking.application.port.`in`.RequestFirmBankingCommand
import com.psh10066.banking.application.port.`in`.RequestFirmBankingUseCase
import com.psh10066.banking.application.port.out.RequestExternalFirmBankingPort
import com.psh10066.banking.application.port.out.RequestFirmBankingPort
import com.psh10066.banking.domain.FirmBankingRequest
import com.psh10066.common.UseCase
import org.springframework.transaction.annotation.Transactional
import java.util.*

@UseCase
@Transactional
class RequestFirmBankingService(
    private val requestFirmBankingPort: RequestFirmBankingPort,
    private val requestExternalFirmBankingPort: RequestExternalFirmBankingPort,
    private val firmBankingRequestMapper: FirmBankingRequestMapper
) : RequestFirmBankingUseCase {

    override fun requestFirmBanking(command: RequestFirmBankingCommand): FirmBankingRequest {
        val requestedJpaEntity = requestFirmBankingPort.createFirmBankingRequest(
            fromBankName = command.fromBankName,
            fromBankAccountNumber = command.fromBankAccountNumber,
            toBankName = command.toBankName,
            toBankAccountNumber = command.toBankAccountNumber,
            moneyAmount = command.moneyAmount,
            firmBankingStatus = FirmBankingStatus.REQUESTED
        )

        val result = requestExternalFirmBankingPort.requestExternalFirmBanking(
            ExternalFirmBankingRequest(
                fromBankName = command.fromBankName,
                fromBankAccountNumber = command.fromBankAccountNumber,
                toBankName = command.toBankName,
                toBankAccountNumber = command.toBankAccountNumber
            )
        )

        val randomUUID = UUID.randomUUID()
        requestedJpaEntity.uuid = randomUUID.toString()

        if (result.resultCode == 0) {
            requestedJpaEntity.firmBankingStatus = FirmBankingStatus.SUCCESS
        } else {
            requestedJpaEntity.firmBankingStatus = FirmBankingStatus.FAIL
        }

        return firmBankingRequestMapper.mapToDomainEntity(
            requestFirmBankingPort.modifyFirmBankingRequest(
                requestedJpaEntity
            )
        )
    }
}