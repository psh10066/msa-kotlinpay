package com.psh10066.money.application.port.out

import com.psh10066.common.type.RechargingMoneyTask

interface SendRechargingMoneyTaskPort {

    fun sendRechargingMoneyTaskPort(task: RechargingMoneyTask)
}