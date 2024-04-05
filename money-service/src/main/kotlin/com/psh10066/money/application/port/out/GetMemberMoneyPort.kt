package com.psh10066.money.application.port.out

import com.psh10066.money.adapter.out.persistence.MemberMoneyJpaEntity

interface GetMemberMoneyPort {

    fun getMemberMoney(membershipId: Long): MemberMoneyJpaEntity
}