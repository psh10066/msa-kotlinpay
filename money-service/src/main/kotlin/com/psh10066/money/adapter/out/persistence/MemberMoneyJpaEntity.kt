package com.psh10066.money.adapter.out.persistence

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "member_money")
class MemberMoneyJpaEntity(

    @field:Id
    @field:GeneratedValue
    val memberMoneyId: Long? = null,

    var membershipId: Long? = null,

    var moneyBalance: Int? = null,
)