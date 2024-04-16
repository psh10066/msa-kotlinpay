package com.psh10066.common.event

import com.psh10066.common.type.BankName

class CheckedRegisteredBankAccountEvent(
    val rechargingRequestId: String,
    val checkRegisteredBankAccountId: String,
    val membershipId: Long,
    val isChecked: Boolean,
    val amount: Long,
    val firmBankingRequestAggregateIdentifier: String,
    val fromBankName: BankName,
    val fromBankAccountNumber: String
) {
    override fun toString(): String {
        return "CheckedRegisteredBankAccountEvent(rechargingRequestId='$rechargingRequestId', checkRegisteredBankAccountId='$checkRegisteredBankAccountId', membershipId=$membershipId, isChecked=$isChecked, amount=$amount, firmBankingRequestAggregateIdentifier='$firmBankingRequestAggregateIdentifier', fromBankName=$fromBankName, fromBankAccountNumber='$fromBankAccountNumber')"
    }
}