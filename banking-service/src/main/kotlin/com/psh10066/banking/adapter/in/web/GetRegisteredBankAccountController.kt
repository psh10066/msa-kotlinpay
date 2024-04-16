package com.psh10066.banking.adapter.`in`.web

import com.psh10066.banking.application.port.`in`.GetRegisteredBankAccountCommand
import com.psh10066.banking.application.port.`in`.GetRegisteredBankAccountUseCase
import com.psh10066.banking.domain.RegisteredBankAccount
import com.psh10066.common.annotation.WebAdapter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class GetRegisteredBankAccountController(
    private val getRegisteredBankAccountUseCase: GetRegisteredBankAccountUseCase
) {

    @GetMapping(path = ["/banking/account/{membershipId}"])
    fun getRegisteredBankAccount(@PathVariable membershipId: Long): RegisteredBankAccount {

        val command = GetRegisteredBankAccountCommand(membershipId)
        return getRegisteredBankAccountUseCase.getRegisteredBankAccount(command)
    }
}