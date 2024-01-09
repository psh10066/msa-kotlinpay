package com.psh10066.money.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataMoneyChangingRequestRepository : JpaRepository<MoneyChangingRequestJpaEntity, Long>