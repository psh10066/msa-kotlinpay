package com.psh10066.banking.application.port.out

import com.psh10066.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity
import com.psh10066.common.BankName

interface RegisterBankAccountPort {

    fun createRegisteredBankAccount(
        membershipId: Long,
        bankName: BankName,
        bankAccountNumber: String,
        linkedStatusIsValid: Boolean
    ): RegisteredBankAccountJpaEntity
}