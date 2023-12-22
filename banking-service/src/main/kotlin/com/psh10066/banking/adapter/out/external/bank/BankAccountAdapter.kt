package com.psh10066.banking.adapter.out.external.bank

import com.psh10066.banking.application.port.out.RequestBankAccountInfoPort
import com.psh10066.common.ExternalSystemAdapter

@ExternalSystemAdapter
class BankAccountAdapter : RequestBankAccountInfoPort {
    override fun getBankAccountInfo(request: GetBankAccountRequest): BankAccount {

        // TODO: 은행에 요청을 통해 등록 가능한지 확인한다고 가정
        return BankAccount(
            bankName = request.bankName,
            bankAccountNumber = request.bankAccountNumber,
            isValid = true
        )
    }
}