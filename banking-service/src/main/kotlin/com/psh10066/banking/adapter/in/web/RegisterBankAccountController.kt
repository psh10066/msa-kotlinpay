package com.psh10066.banking.adapter.`in`.web

import com.psh10066.banking.application.port.`in`.RegisterBankAccountCommand
import com.psh10066.banking.application.port.`in`.RegisterBankAccountUseCase
import com.psh10066.banking.domain.RegisteredBankAccount
import com.psh10066.common.annotation.WebAdapter
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class RegisterBankAccountController(
    private val registerMembershipUseCase: RegisterBankAccountUseCase
) {

    @PostMapping(path = ["/banking/account/register"])
    fun registerBankAccount(@RequestBody request: RegisterBankAccountRequest): RegisteredBankAccount {

        val command = RegisterBankAccountCommand(
            membershipId = request.membershipId,
            bankName = request.bankName,
            bankAccountNumber = request.bankAccountNumber,
            linkedStatusIsValid = request.linkedStatusIsValid
        )

        return registerMembershipUseCase.registerBankAccount(command)
    }

    @PostMapping(path = ["/banking/account/register-eda"])
    fun registerBankAccountByEvent(@RequestBody request: RegisterBankAccountRequest) {

        val command = RegisterBankAccountCommand(
            membershipId = request.membershipId,
            bankName = request.bankName,
            bankAccountNumber = request.bankAccountNumber,
            linkedStatusIsValid = request.linkedStatusIsValid
        )

        registerMembershipUseCase.registerBankAccountByEvent(command)
    }
}