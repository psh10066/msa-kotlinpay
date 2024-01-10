package com.psh10066.banking.application.service

import com.psh10066.banking.adapter.out.external.bank.GetBankAccountRequest
import com.psh10066.banking.adapter.out.persistence.RegisteredBankAccountMapper
import com.psh10066.banking.application.port.`in`.RegisterBankAccountCommand
import com.psh10066.banking.application.port.`in`.RegisterBankAccountUseCase
import com.psh10066.banking.application.port.out.GetMembershipPort
import com.psh10066.banking.application.port.out.RegisterBankAccountPort
import com.psh10066.banking.application.port.out.RequestBankAccountInfoPort
import com.psh10066.banking.domain.RegisteredBankAccount
import com.psh10066.common.UseCase
import org.springframework.transaction.annotation.Transactional

@UseCase
@Transactional
class RegisterBankAccountService(
    private val getMembershipPort: GetMembershipPort,
    private val registerMembershipPort: RegisterBankAccountPort,
    private val requestBankAccountInfoPort: RequestBankAccountInfoPort,
    private val bankAccountMapper: RegisteredBankAccountMapper
) : RegisterBankAccountUseCase {
    override fun registerBankAccount(command: RegisterBankAccountCommand): RegisteredBankAccount {

        getMembershipPort.getMembership(membershipId = command.membershipId).also {
            if (it?.isValid != true) throw RuntimeException("인증되지 않은 회원입니다.")
        }

        val accountInfo = requestBankAccountInfoPort.getBankAccountInfo(
            GetBankAccountRequest(
                bankName = command.bankName,
                bankAccountNumber = command.bankAccountNumber
            )
        )

        if (!accountInfo.isValid) {
            throw RuntimeException("등록 가능한 계좌 정보가 아닙니다.")
        }

        val jpaEntity = registerMembershipPort.createRegisteredBankAccount(
            membershipId = command.membershipId,
            bankName = command.bankName,
            bankAccountNumber = command.bankAccountNumber,
            linkedStatusIsValid = command.linkedStatusIsValid
        )

        return bankAccountMapper.mapToDomainEntity(jpaEntity)
    }
}