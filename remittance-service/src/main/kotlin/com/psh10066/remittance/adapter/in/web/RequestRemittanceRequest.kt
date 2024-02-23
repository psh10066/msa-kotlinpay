package com.psh10066.remittance.adapter.`in`.web

import com.psh10066.common.type.BankName
import com.psh10066.remittance.application.port.`in`.RemittanceType

data class RequestRemittanceRequest(
    val fromMembershipId: Long?,
    val toMembershipId: Long?,
    val toBankName: BankName?,
    val toBankAccountNumber: String?,
    val remittanceType: RemittanceType?,
    val amount: Int?,
)