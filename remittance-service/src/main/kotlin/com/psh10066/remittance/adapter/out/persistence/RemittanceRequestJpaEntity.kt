package com.psh10066.remittance.adapter.out.persistence

import com.psh10066.remittance.application.port.`in`.RemittanceType
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

    @field:Enumerated(EnumType.STRING)
    val remittanceType: RemittanceType? = null,

    val amount: Int? = null,

    @field:Enumerated(EnumType.STRING)
    var remittanceStatus: RemittanceStatus? = null
)