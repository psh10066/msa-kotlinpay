package com.psh10066.remittance.adapter.out.persistence

import com.psh10066.common.type.BankName
import com.psh10066.remittance.application.port.`in`.RemittanceType
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Comment("송금 요청")
@Entity
@Table(
    name = "request_remittance",
    indexes = [
        Index(name = "fromMembershipId", columnList = "fromMembershipId"),
        Index(name = "toMembershipId", columnList = "toMembershipId"),
    ]
)
class RemittanceRequestJpaEntity(

    @field:Comment("송금 요청 고유번호")
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val remittanceRequestId: Long? = null,

    @field:Comment("출금 회원 고유번호")
    @field:Column(nullable = false)
    val fromMembershipId: Long? = null,

    @field:Comment("입금 회원 고유번호")
    val toMembershipId: Long? = null,

    @field:Comment("입금 은행명")
    @field:Enumerated(EnumType.STRING)
    val toBankName: BankName? = null,

    @field:Comment("입금 계좌번호")
    val toBankAccountNumber: String? = null,

    @field:Comment("송금 요청 유형")
    @field:Column(nullable = false)
    @field:Enumerated(EnumType.STRING)
    val remittanceType: RemittanceType? = null,

    @field:Comment("금액")
    @field:Column(nullable = false)
    val amount: Int? = null,

    @field:Comment("송금 요청 상태")
    @field:Column(nullable = false)
    @field:Enumerated(EnumType.STRING)
    var remittanceStatus: RemittanceStatus? = null
)