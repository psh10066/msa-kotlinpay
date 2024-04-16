package com.psh10066.common.event

import com.psh10066.common.type.BankName

class RequestFirmBankingFinishedEvent(
    val requestFirmBankingId: String,
    val rechargingRequestId: String,
    val membershipId: Long,
    val fromBankName: BankName,
    val fromBankAccountNumber: String,
    val toBankName: BankName,
    val toBankAccountNumber: String,
    val moneyAmount: Long,
    val status: Int,
    val requestFirmBankingAggregateIdentifier: String,
) {
    override fun toString(): String {
        return "RequestFirmBankingFinishedEvent(requestFirmBankingId='$requestFirmBankingId', rechargingRequestId='$rechargingRequestId', membershipId=$membershipId, fromBankName=$fromBankName, fromBankAccountNumber='$fromBankAccountNumber', toBankName=$toBankName, toBankAccountNumber='$toBankAccountNumber', moneyAmount=$moneyAmount, status=$status, requestFirmBankingAggregateIdentifier='$requestFirmBankingAggregateIdentifier')"
    }
}