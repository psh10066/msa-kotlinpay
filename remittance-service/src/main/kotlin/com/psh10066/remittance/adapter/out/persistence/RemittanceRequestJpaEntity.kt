package com.psh10066.remittance.adapter.out.persistence

import jakarta.persistence.*

@Entity
@Table(name = "request_remittance")
class RemittanceRequestJpaEntity(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val remittanceRequestId: Long? = null,
    val fromMembershipId: Long? = null,
    val toMembershipId: Long? = null,
    val toBankName: String? = null,
    val toBankAccountNumber: String? = null,
    val remittanceType: Int? = null, // 0: membership (내부 고객), 1: bank (외부 은행 계좌)
    val amount: Int? = null,
    var remittanceStatus: String? = null
)