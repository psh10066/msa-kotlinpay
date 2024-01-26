package com.psh10066.remittance.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataRemittanceRequestRepository : JpaRepository<RemittanceRequestJpaEntity, Long> {
}