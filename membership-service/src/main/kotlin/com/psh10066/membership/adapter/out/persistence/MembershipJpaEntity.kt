package com.psh10066.membership.adapter.out.persistence

import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Comment("회원")
@Entity
@Table(name = "membership")
class MembershipJpaEntity(

    @field:Comment("회원 고유번호")
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val membershipId: Long? = null,

    @field:Comment("회원 이름")
    @field:Column(nullable = false)
    var name: String? = null,

    @field:Comment("회원 주소")
    @field:Column(nullable = false)
    var address: String? = null,

    @field:Comment("회원 이메일")
    @field:Column(nullable = false)
    var email: String? = null,

    @field:Comment("유효 회원 여부")
    @field:Column(nullable = false)
    var isValid: Boolean? = null,

    @field:Comment("법인 회원 여부")
    @field:Column(nullable = false)
    var isCorp: Boolean? = null
)