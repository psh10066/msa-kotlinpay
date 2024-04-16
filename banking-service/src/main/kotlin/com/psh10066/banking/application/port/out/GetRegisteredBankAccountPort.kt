package com.psh10066.banking.application.port.out

import com.psh10066.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity
import com.psh10066.banking.application.port.`in`.GetRegisteredBankAccountCommand

interface GetRegisteredBankAccountPort {

    fun getRegisteredBankAccount(command: GetRegisteredBankAccountCommand): RegisteredBankAccountJpaEntity
}