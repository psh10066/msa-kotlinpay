package com.psh10066.banking.adapter.out.persistence

import com.psh10066.banking.application.port.out.RegisterBankAccountPort
import com.psh10066.common.PersistenceAdapter

@PersistenceAdapter
class RegisteredBankAccountPersistenceAdapter(
    val bankAccountRepository: SpringDataRegisteredBankAccountRepository
) : RegisterBankAccountPort {
    override fun createRegisteredBankAccount(
        membershipId: Long,
        bankName: String,
        bankAccountNumber: String,
        linkedStatusIsValid: Boolean
    ): RegisteredBankAccountJpaEntity {
        return bankAccountRepository.save(
            RegisteredBankAccountJpaEntity(
                membershipId = membershipId,
                bankName = bankName,
                bankAccountNumber = bankAccountNumber,
                linkedStatusIsValid = linkedStatusIsValid
            )
        )
    }

}