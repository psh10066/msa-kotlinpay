package com.psh10066.banking.application.port.`in`

import com.psh10066.banking.domain.RegisteredBankAccount

interface GetRegisteredBankAccountUseCase {

    fun getRegisteredBankAccount(command: GetRegisteredBankAccountCommand): RegisteredBankAccount
}