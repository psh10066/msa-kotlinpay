package com.psh10066.membership.adapter.out.persistence

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "membership")
class MembershipJpaEntity(

    @field:Id
    @field:GeneratedValue
    val membershipId: Long? = null,

    val name: String? = null,

    val address: String? = null,

    val email: String? = null,

    val isValid: Boolean? = null,

    val isCorp: Boolean? = null
)