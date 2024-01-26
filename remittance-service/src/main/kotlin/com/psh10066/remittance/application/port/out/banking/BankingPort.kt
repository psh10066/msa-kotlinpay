package com.psh10066.remittance.application.port.out.banking

interface BankingPort {

    fun getMembershipBankingInfo(bankName: String, bankAccountNumber: String): BankingInfo?
    fun requestFirmBanking(bankName: String, bankAccountNumber: String, amount: Int): Boolean
}