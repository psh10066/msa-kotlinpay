package com.psh10066.money.adapter.out.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.psh10066.common.CommonHttpClient
import com.psh10066.money.application.port.out.GetRegisteredBankAccountPort
import com.psh10066.money.application.port.out.RegisteredBankAccountAggregateIdentifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class BankingServiceAdapter(
    private val commonHttpClient: CommonHttpClient,

    @Value("\${service.banking.url}")
    private val bankServiceUrl: String
) : GetRegisteredBankAccountPort {

    override fun getRegisteredBankAccount(membershipId: Long): RegisteredBankAccountAggregateIdentifier {

        val url = "$bankServiceUrl/banking/account/$membershipId"

        val jsonResponse = commonHttpClient.sendGetRequest(url).body()

        // json Membership
        val mapper = ObjectMapper().registerKotlinModule()
        val registeredBankAccount = mapper.readValue(jsonResponse, RegisteredBankAccount::class.java)

        return RegisteredBankAccountAggregateIdentifier(
            registeredBankAccountId = registeredBankAccount.registeredBankAccountId,
            aggregateIdentifier = registeredBankAccount.aggregateIdentifier,
            membershipId = registeredBankAccount.membershipId,
            bankName = registeredBankAccount.bankName,
            bankAccountNumber = registeredBankAccount.bankAccountNumber
        )
    }
}