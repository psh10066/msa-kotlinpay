package com.psh10066.common.type

data class SubTask(
    val membershipId: Long,
    val subTaskName: String,
    val taskType: String,
    var status: SubTaskStatus,
)