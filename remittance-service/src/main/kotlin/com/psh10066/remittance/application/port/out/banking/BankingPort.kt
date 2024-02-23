package com.psh10066.remittance.application.port.out.banking

import com.psh10066.common.type.BankName

interface BankingPort {

    fun getMembershipBankingInfo(bankName: BankName, bankAccountNumber: String): BankingInfo?
    fun requestFirmBanking(bankName: BankName, bankAccountNumber: String, amount: Int): Boolean
}