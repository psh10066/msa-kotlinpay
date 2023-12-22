package com.psh10066.banking.application.port.out

import com.psh10066.banking.adapter.out.external.bank.BankAccount
import com.psh10066.banking.adapter.out.external.bank.GetBankAccountRequest

interface RequestBankAccountInfoPort {
    fun getBankAccountInfo(request: GetBankAccountRequest): BankAccount
}