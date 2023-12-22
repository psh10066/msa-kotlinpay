package com.psh10066.banking.application.port.`in`

import com.psh10066.banking.domain.RegisteredBankAccount

interface RegisterBankAccountUseCase {

    fun registerBankAccount(command: RegisterBankAccountCommand): RegisteredBankAccount
}