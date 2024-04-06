package com.psh10066.banking.application.service

import com.psh10066.banking.adapter.axon.command.CreateFirmBankingRequestCommand
import com.psh10066.banking.adapter.axon.command.UpdateFirmBankingRequestCommand
import com.psh10066.banking.adapter.out.external.bank.ExternalFirmBankingRequest
import com.psh10066.banking.adapter.out.persistence.FirmBankingRequestMapper
import com.psh10066.banking.adapter.out.persistence.FirmBankingStatus
import com.psh10066.banking.application.port.`in`.RequestFirmBankingCommand
import com.psh10066.banking.application.port.`in`.RequestFirmBankingUseCase
import com.psh10066.banking.application.port.`in`.UpdateFirmBankingCommand
import com.psh10066.banking.application.port.`in`.UpdateFirmBankingUseCase
import com.psh10066.banking.application.port.out.RequestExternalFirmBankingPort
import com.psh10066.banking.application.port.out.RequestFirmBankingPort
import com.psh10066.banking.domain.FirmBankingRequest
import com.psh10066.common.annotation.UseCase
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.transaction.annotation.Transactional

@UseCase
@Transactional
class RequestFirmBankingService(
    private val requestFirmBankingPort: RequestFirmBankingPort,
    private val requestExternalFirmBankingPort: RequestExternalFirmBankingPort,
    private val firmBankingRequestMapper: FirmBankingRequestMapper,
    private val commandGateway: CommandGateway
) : RequestFirmBankingUseCase, UpdateFirmBankingUseCase {

    override fun requestFirmBanking(command: RequestFirmBankingCommand): FirmBankingRequest {
        val requestedJpaEntity = requestFirmBankingPort.createFirmBankingRequest(
            fromBankName = command.fromBankName,
            fromBankAccountNumber = command.fromBankAccountNumber,
            toBankName = command.toBankName,
            toBankAccountNumber = command.toBankAccountNumber,
            moneyAmount = command.moneyAmount,
            firmBankingStatus = FirmBankingStatus.REQUESTED,
            aggregateIdentifier = ""
        )

        val result = requestExternalFirmBankingPort.requestExternalFirmBanking(
            ExternalFirmBankingRequest(
                fromBankName = command.fromBankName,
                fromBankAccountNumber = command.fromBankAccountNumber,
                toBankName = command.toBankName,
                toBankAccountNumber = command.toBankAccountNumber
            )
        )

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

    override fun requestFirmBankingByEvent(command: RequestFirmBankingCommand) {
        val axonCommand = CreateFirmBankingRequestCommand(
            fromBankName = command.fromBankName,
            fromBankAccountNumber = command.fromBankAccountNumber,
            toBankName = command.toBankName,
            toBankAccountNumber = command.toBankAccountNumber,
            moneyAmount = command.moneyAmount
        )
        commandGateway.send<String>(axonCommand).whenComplete { result, throwable ->
            if (throwable != null) {
                throwable.printStackTrace()
            } else {
                println("CreateFirmBankingRequestCommand completed, Aggregate ID : $result")
                val requestedJpaEntity = requestFirmBankingPort.createFirmBankingRequest(
                    fromBankName = command.fromBankName,
                    fromBankAccountNumber = command.fromBankAccountNumber,
                    toBankName = command.toBankName,
                    toBankAccountNumber = command.toBankAccountNumber,
                    moneyAmount = command.moneyAmount,
                    firmBankingStatus = FirmBankingStatus.REQUESTED,
                    aggregateIdentifier = result
                )

                val firmBankingResult = requestExternalFirmBankingPort.requestExternalFirmBanking(
                    ExternalFirmBankingRequest(
                        fromBankName = command.fromBankName,
                        fromBankAccountNumber = command.fromBankAccountNumber,
                        toBankName = command.toBankName,
                        toBankAccountNumber = command.toBankAccountNumber
                    )
                )

                if (firmBankingResult.resultCode == 0) {
                    requestedJpaEntity.firmBankingStatus = FirmBankingStatus.SUCCESS
                } else {
                    requestedJpaEntity.firmBankingStatus = FirmBankingStatus.FAIL
                }
                requestFirmBankingPort.modifyFirmBankingRequest(
                    requestedJpaEntity
                )
            }
        }
    }

    override fun updateFirmBankingByEvent(command: UpdateFirmBankingCommand) {
        val axonCommand = UpdateFirmBankingRequestCommand(
            aggregateIdentifier = command.firmBankingAggregateIdentifier,
            firmBankingStatus = command.firmBankingStatus
        )
        commandGateway.send<String>(axonCommand).whenComplete { result, throwable ->
            if (throwable != null) {
                throwable.printStackTrace()
            } else {
                println("UpdateFirmBankingRequestCommand completed, Aggregate ID : $result")
                val entity = requestFirmBankingPort.getFirmBankingRequest(command.firmBankingAggregateIdentifier)
                entity.firmBankingStatus = command.firmBankingStatus
                requestFirmBankingPort.modifyFirmBankingRequest(entity)
            }
        }
    }
}