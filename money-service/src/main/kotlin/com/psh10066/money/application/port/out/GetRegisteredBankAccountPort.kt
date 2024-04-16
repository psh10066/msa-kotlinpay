package com.psh10066.money.application.port.out

interface GetRegisteredBankAccountPort {

    fun getRegisteredBankAccount(membershipId: Long): RegisteredBankAccountAggregateIdentifier
}