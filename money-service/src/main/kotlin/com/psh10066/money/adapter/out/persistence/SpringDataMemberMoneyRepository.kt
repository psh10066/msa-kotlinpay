package com.psh10066.money.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataMemberMoneyRepository : JpaRepository<MemberMoneyJpaEntity, Long> {
    fun findByMembershipId(membershipId: Long): MemberMoneyJpaEntity?
}