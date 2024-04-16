package com.psh10066.banking.adapter.out.persistence

import com.psh10066.banking.domain.RegisteredBankAccount
import org.springframework.stereotype.Component

@Component
class RegisteredBankAccountMapper {

    fun mapToDomainEntity(registeredBankAccountJpaEntity: RegisteredBankAccountJpaEntity): RegisteredBankAccount {
        return RegisteredBankAccount(
            registeredBankAccountId = registeredBankAccountJpaEntity.registeredBankAccountId,
            membershipId = registeredBankAccountJpaEntity.membershipId,
            bankName = registeredBankAccountJpaEntity.bankName,
            bankAccountNumber = registeredBankAccountJpaEntity.bankAccountNumber,
            linkedStatusIsValid = registeredBankAccountJpaEntity.linkedStatusIsValid,
            aggregateIdentifier = registeredBankAccountJpaEntity.aggregateIdentifier
        )
    }
}