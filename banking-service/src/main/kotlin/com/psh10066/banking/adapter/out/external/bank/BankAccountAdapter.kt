package com.psh10066.banking.adapter.out.external.bank

import com.psh10066.banking.application.port.out.RequestBankAccountInfoPort
import com.psh10066.banking.application.port.out.RequestExternalFirmBankingPort
import com.psh10066.common.annotation.ExternalSystemAdapter

@ExternalSystemAdapter
class BankAccountAdapter : RequestBankAccountInfoPort, RequestExternalFirmBankingPort {
    override fun getBankAccountInfo(request: GetBankAccountRequest): BankAccount {

        // TODO: 은행에 요청을 통해 등록 가능한지 확인한다고 가정
        return BankAccount(
            bankName = request.bankName,
            bankAccountNumber = request.bankAccountNumber,
            isValid = true
        )
    }

    override fun requestExternalFirmBanking(request: ExternalFirmBankingRequest): FirmBankingResult {

        // TODO: 펌뱅킹에 요청 후 외부 은행의 실제 결과를 파싱
        return FirmBankingResult(resultCode = 0)
    }
}