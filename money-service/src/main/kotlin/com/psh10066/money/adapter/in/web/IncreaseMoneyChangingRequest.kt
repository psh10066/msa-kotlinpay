package com.psh10066.money.adapter.`in`.web

data class IncreaseMoneyChangingRequest(
    val targetMembershipId: Long,
    val amount: Int,
)