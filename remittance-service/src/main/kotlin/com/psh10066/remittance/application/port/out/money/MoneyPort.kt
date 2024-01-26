package com.psh10066.remittance.application.port.out.money

interface MoneyPort {

    fun getMoneyInfo(membershipId: Long): MoneyInfo
    fun requestMoneyRecharging(membershipId: Long, amount: Int): Boolean
    fun requestMoneyIncrease(membershipId: Long?, amount: Int): Boolean
    fun requestMoneyDecrease(membershipId: Long?, amount: Int): Boolean
}