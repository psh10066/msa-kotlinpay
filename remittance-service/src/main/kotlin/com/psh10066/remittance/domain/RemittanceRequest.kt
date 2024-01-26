package com.psh10066.remittance.domain

// 송금 요청 상태 클래스
data class RemittanceRequest(
    val remittanceRequestId: Long?,
    val remittanceFromMembershipId: Long?,
    val toBankName: String?,
    val toBankAccountNumber: String?,
    val remittanceType: Int?, // 0: membership (내부 고객), 1: bank (외부 은행 계좌)
    val amount: Int?,
)