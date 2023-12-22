package com.psh10066.banking.application.port.out

import com.psh10066.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity

interface RegisterBankAccountPort {

    fun createRegisteredBankAccount(
        membershipId: Long,
        bankName: String,
        bankAccountNumber: String,
        linkedStatusIsValid: Boolean
    ): RegisteredBankAccountJpaEntity
}