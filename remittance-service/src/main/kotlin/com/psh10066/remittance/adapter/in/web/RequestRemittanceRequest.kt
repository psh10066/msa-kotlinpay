package com.psh10066.remittance.adapter.`in`.web

data class RequestRemittanceRequest(
    val fromMembershipId: Long?,
    val toMembershipId: Long?,
    val toBankName: String?,
    val toBankAccountNumber: String?,
    val remittanceType: Int?, // 0: membership (내부 고객), 1: bank (외부 은행 계좌)
    val amount: Int?,
)