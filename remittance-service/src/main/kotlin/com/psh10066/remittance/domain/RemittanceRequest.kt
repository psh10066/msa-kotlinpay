package com.psh10066.remittance.domain

import com.psh10066.remittance.application.port.`in`.RemittanceType

// 송금 요청 상태 클래스
data class RemittanceRequest(
    val remittanceRequestId: Long?,
    val remittanceFromMembershipId: Long?,
    val toBankName: String?,
    val toBankAccountNumber: String?,
    val remittanceType: RemittanceType?,
    val amount: Int?,
)