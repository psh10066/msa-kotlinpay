package com.psh10066.remittance.adapter.out.service.money

import com.psh10066.common.CommonHttpClient
import com.psh10066.common.annotation.ExternalSystemAdapter
import com.psh10066.remittance.application.port.out.money.MoneyInfo
import com.psh10066.remittance.application.port.out.money.MoneyPort
import org.springframework.beans.factory.annotation.Value

@ExternalSystemAdapter
class MoneyServiceAdapter(
    private val commonHttpClient: CommonHttpClient,

    @Value("\${service.money.url}")
    private val moneyServiceUrl: String
) : MoneyPort {
    override fun getMoneyInfo(membershipId: Long): MoneyInfo {
        TODO("Not yet implemented")
    }

    override fun requestMoneyRecharging(membershipId: Long, amount: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun requestMoneyIncrease(membershipId: Long?, amount: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun requestMoneyDecrease(membershipId: Long?, amount: Int): Boolean {
        TODO("Not yet implemented")
    }
}