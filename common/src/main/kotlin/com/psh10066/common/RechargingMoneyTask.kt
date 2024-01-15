package com.psh10066.common

data class RechargingMoneyTask(

    val taskID: String,
    val taskName: String,

    val membershipID: Long,

    val subTaskList: List<SubTask>,

    // 법인 계좌
    val toBankName: String,

    // 법인 계좌 번호
    val toBankAccountNumber: String? = null,

    val moneyAmount: Int = 0
)