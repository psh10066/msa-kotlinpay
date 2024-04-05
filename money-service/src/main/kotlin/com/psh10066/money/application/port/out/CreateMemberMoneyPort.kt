package com.psh10066.money.application.port.out

interface CreateMemberMoneyPort {

    fun createMemberMoney(membershipId: Long, aggregateIdentifier: String)
}