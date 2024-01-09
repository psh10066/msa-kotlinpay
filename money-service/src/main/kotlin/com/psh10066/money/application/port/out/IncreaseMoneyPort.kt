package com.psh10066.money.application.port.out

import com.psh10066.money.adapter.out.persistence.MemberMoneyJpaEntity
import com.psh10066.money.adapter.out.persistence.MoneyChangingRequestJpaEntity
import com.psh10066.money.domain.MoneyChangingStatus
import com.psh10066.money.domain.MoneyChangingType

interface IncreaseMoneyPort {

    fun createMoneyChangingRequest(
        targetMemberId: Long,
        moneyChangingType: MoneyChangingType,
        changingMoneyAccount: Int,
        moneyChangingStatus: MoneyChangingStatus,
    ): MoneyChangingRequestJpaEntity

    fun increaseMoney(
        membershipId: Long,
        moneyAmount: Int
    ): MemberMoneyJpaEntity
}