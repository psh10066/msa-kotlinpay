package com.psh10066.money.adapter.out.persistence

import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Comment("회원 잔고")
@Entity
@Table(
    name = "member_money",
    indexes = [
        Index(name = "membershipId", columnList = "membershipId"),
    ]
)
class MemberMoneyJpaEntity(

    @field:Comment("회원 잔고 고유번호")
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val memberMoneyId: Long? = null,

    @field:Comment("회원 고유번호")
    @field:Column(nullable = false)
    var membershipId: Long? = null,

    @field:Comment("회원 잔고 금액")
    @field:Column(nullable = false)
    var moneyBalance: Int? = null,
)