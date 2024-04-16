package com.psh10066.banking.application.service

import com.psh10066.banking.adapter.axon.command.CreateRegisteredBankAccountCommand
import com.psh10066.banking.adapter.out.external.bank.GetBankAccountRequest
import com.psh10066.banking.adapter.out.persistence.RegisteredBankAccountMapper
import com.psh10066.banking.application.port.`in`.GetRegisteredBankAccountCommand
import com.psh10066.banking.application.port.`in`.GetRegisteredBankAccountUseCase
import com.psh10066.banking.application.port.`in`.RegisterBankAccountCommand
import com.psh10066.banking.application.port.`in`.RegisterBankAccountUseCase
import com.psh10066.banking.application.port.out.GetMembershipPort
import com.psh10066.banking.application.port.out.GetRegisteredBankAccountPort
import com.psh10066.banking.application.port.out.RegisterBankAccountPort
import com.psh10066.banking.application.port.out.RequestBankAccountInfoPort
import com.psh10066.banking.domain.RegisteredBankAccount
import com.psh10066.common.annotation.UseCase
import com.psh10066.common.exception.CustomException
import com.psh10066.common.exception.ErrorType
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.transaction.annotation.Transactional

@UseCase
@Transactional
class RegisterBankAccountService(
    private val getMembershipPort: GetMembershipPort,
    private val registerMembershipPort: RegisterBankAccountPort,
    private val requestBankAccountInfoPort: RequestBankAccountInfoPort,
    private val bankAccountMapper: RegisteredBankAccountMapper,
    private val getRegisteredBankAccountPort: GetRegisteredBankAccountPort,
    private val commandGateway: CommandGateway
) : RegisterBankAccountUseCase, GetRegisteredBankAccountUseCase {
    override fun registerBankAccount(command: RegisterBankAccountCommand): RegisteredBankAccount {

        getMembershipPort.getMembership(membershipId = command.membershipId).also {
            if (it?.isValid != true) throw CustomException(ErrorType.UNAUTHORIZED_MEMBER)
        }

        val accountInfo = requestBankAccountInfoPort.getBankAccountInfo(
            GetBankAccountRequest(
                bankName = command.bankName,
                bankAccountNumber = command.bankAccountNumber
            )
        )

        if (!accountInfo.isValid) {
            throw CustomException(ErrorType.NOT_VALID_ACCOUNT)
        }

        val jpaEntity = registerMembershipPort.createRegisteredBankAccount(
            membershipId = command.membershipId,
            bankName = command.bankName,
            bankAccountNumber = command.bankAccountNumber,
            linkedStatusIsValid = command.linkedStatusIsValid,
            aggregateIdentifier = ""
        )

        return bankAccountMapper.mapToDomainEntity(jpaEntity)
    }

    override fun registerBankAccountByEvent(command: RegisterBankAccountCommand) {
        commandGateway.send<String>(
            CreateRegisteredBankAccountCommand(
                membershipId = command.membershipId,
                bankName = command.bankName,
                bankAccountNumber = command.bankAccountNumber
            )
        ).whenComplete { result, throwable ->
            if (throwable != null) {
                throwable.printStackTrace()
            } else {
                registerMembershipPort.createRegisteredBankAccount(
                    membershipId = command.membershipId,
                    bankName = command.bankName,
                    bankAccountNumber = command.bankAccountNumber,
                    linkedStatusIsValid = command.linkedStatusIsValid,
                    aggregateIdentifier = result.toString()
                )
            }
        }
    }

    override fun getRegisteredBankAccount(command: GetRegisteredBankAccountCommand): RegisteredBankAccount {
        val jpaEntity = getRegisteredBankAccountPort.getRegisteredBankAccount(command)
        return bankAccountMapper.mapToDomainEntity(jpaEntity)
    }
}