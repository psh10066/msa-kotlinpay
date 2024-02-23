package com.psh10066.remittance.adapter.out.service.banking

import com.psh10066.common.type.BankName
import com.psh10066.common.CommonHttpClient
import com.psh10066.common.annotation.ExternalSystemAdapter
import com.psh10066.remittance.application.port.out.banking.BankingInfo
import com.psh10066.remittance.application.port.out.banking.BankingPort
import org.springframework.beans.factory.annotation.Value

@ExternalSystemAdapter
class BankingServiceAdapter(
    private val commonHttpClient: CommonHttpClient,

    @Value("\${service.banking.url}")
    private val bankingServiceUrl: String
) : BankingPort {
    override fun getMembershipBankingInfo(bankName: BankName, bankAccountNumber: String): BankingInfo? {
        TODO("Not yet implemented")
    }

    override fun requestFirmBanking(bankName: BankName, bankAccountNumber: String, amount: Int): Boolean {
        TODO("Not yet implemented")
    }

}