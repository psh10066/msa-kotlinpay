package com.psh10066.membership.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataMembershipRepository : JpaRepository<MembershipJpaEntity, Long>