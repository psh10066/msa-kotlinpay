package com.psh10066.money.adapter.`in`.web

data class MoneyChangingResultDetail(
    val moneyChangingRequestId: Long?,
    val moneyChangingType: com.psh10066.money.domain.MoneyChangingType?,
    val moneyChangingResultStatus: MoneyChangingResultStatus?,
    val amount: Int?,
)

enum class MoneyChangingResultStatus {
    SUCCEEDED,
    FAILED,
    FAILED_NOT_ENOUGH_MONEY, // 실패 - 잔액 부족
    FAILED_NOT_EXIST_MEMBERSHIP, // 실패 - 멤버십 없음
    FAILED_NOT_EXIST_MONEY_CHANGING_REQUEST, // 실패 - 변액 요청 없음
}