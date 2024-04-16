package com.psh10066.common.event

class RollbackFirmBankingFinishedEvent(
    val rollbackFirmBankingId: String,
    val membershipId: Long,
    val rollbackFirmBankingAggregateIdentifier: String,
) {
    override fun toString(): String {
        return "RollbackFirmBankingFinishedEvent(rollbackFirmBankingId='$rollbackFirmBankingId', membershipId=$membershipId, rollbackFirmBankingAggregateIdentifier='$rollbackFirmBankingAggregateIdentifier')"
    }
}