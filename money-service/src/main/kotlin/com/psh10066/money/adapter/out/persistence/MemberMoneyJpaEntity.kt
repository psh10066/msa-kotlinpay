package com.psh10066.money.adapter.out.persistence

import jakarta.persistence.*

@Entity
@Table(name = "member_money")
class MemberMoneyJpaEntity(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val memberMoneyId: Long? = null,

    var membershipId: Long? = null,

    var moneyBalance: Int? = null,
)