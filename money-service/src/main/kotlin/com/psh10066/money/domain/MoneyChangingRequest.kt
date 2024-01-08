package com.psh10066.money.domain

import java.time.LocalDateTime
import java.util.UUID

data class MoneyChangingRequest(
    val moneyChangingRequestId: Long?,

    // 어떤 고객의 증액/감액 요청을 했는지의 멤버 정보
    val targetMembershipId: Long?,

    // 그 요청이 증액 요청인지 / 감액 요청인지
    val moneyChangingType: com.psh10066.money.domain.MoneyChangingType?,

    // 증액 또는 감액 요청의 금액
    val moneyChangingAmount: Int?,

    // money 변액 요청에 대한 상태
    val moneyChangingStatus: com.psh10066.money.domain.MoneyChangingStatus?,

    val uuid: UUID?,

    val createdAt: LocalDateTime? = LocalDateTime.now(),
)

enum class MoneyChangingType {
    INCREASING,
    DECREASING
}

enum class MoneyChangingStatus {
    REQUESTED,
    SUCCEEDED,
    FAILED,
    CANCELLED
}