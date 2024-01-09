package com.psh10066.membership.adapter.out.persistence

import jakarta.persistence.*

@Entity
@Table(name = "membership")
class MembershipJpaEntity(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val membershipId: Long? = null,

    var name: String? = null,

    var address: String? = null,

    var email: String? = null,

    var isValid: Boolean? = null,

    var isCorp: Boolean? = null
)