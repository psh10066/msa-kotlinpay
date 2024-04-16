package com.psh10066.banking.adapter.out.persistence

import com.psh10066.banking.application.port.`in`.GetRegisteredBankAccountCommand
import com.psh10066.banking.application.port.out.GetRegisteredBankAccountPort
import com.psh10066.banking.application.port.out.RegisterBankAccountPort
import com.psh10066.common.type.BankName
import com.psh10066.common.annotation.PersistenceAdapter

@PersistenceAdapter
class RegisteredBankAccountPersistenceAdapter(
    val bankAccountRepository: SpringDataRegisteredBankAccountRepository
) : RegisterBankAccountPort, GetRegisteredBankAccountPort {
    override fun createRegisteredBankAccount(
        membershipId: Long,
        bankName: BankName,
        bankAccountNumber: String,
        linkedStatusIsValid: Boolean,
        aggregateIdentifier: String
    ): RegisteredBankAccountJpaEntity {
        return bankAccountRepository.save(
            RegisteredBankAccountJpaEntity(
                membershipId = membershipId,
                bankName = bankName,
                bankAccountNumber = bankAccountNumber,
                linkedStatusIsValid = linkedStatusIsValid,
                aggregateIdentifier = aggregateIdentifier
            )
        )
    }

    override fun getRegisteredBankAccount(command: GetRegisteredBankAccountCommand): RegisteredBankAccountJpaEntity {
        return bankAccountRepository.findByMembershipId(command.membershipId)
    }

}