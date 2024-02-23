package com.psh10066.banking.adapter.out.external.bank

import com.psh10066.common.BankName

data class BankAccount(
    val bankName: BankName,
    val bankAccountNumber: String,
    val isValid: Boolean
)