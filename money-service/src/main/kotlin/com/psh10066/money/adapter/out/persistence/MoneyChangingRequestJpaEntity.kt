package com.psh10066.money.adapter.out.persistence

import com.psh10066.money.domain.MoneyChangingStatus
import com.psh10066.money.domain.MoneyChangingType
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@Comment("금액 변경 요청")
@Entity
@Table(
    name = "money_changing_request",
    indexes = [
        Index(name = "targetMembershipId", columnList = "targetMembershipId")
    ]
)
class MoneyChangingRequestJpaEntity(

    @field:Comment("금액 변경 요청 고유번호")
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val moneyChangingRequestId: Long? = null,

    @field:Comment("대상 회원 고유번호")
    @field:Column(nullable = false)
    var targetMembershipId: Long? = null,

    @field:Comment("금액 변경 유형")
    @field:Column(nullable = false)
    @field:Enumerated(value = EnumType.STRING)
    var moneyChangingType: MoneyChangingType? = null,

    @field:Comment("금액")
    @field:Column(nullable = false)
    var moneyAmount: Int? = null,

    @field:Comment("생성 시각")
    @field:Column(nullable = false)
    @field:Temporal(TemporalType.TIMESTAMP)
    var timestamp: LocalDateTime? = null,

    @field:Comment("금액 변경 상태")
    @field:Column(nullable = false)
    @field:Enumerated(value = EnumType.STRING)
    var moneyChangingStatus: MoneyChangingStatus? = null,

    @field:Comment("Transaction UUID")
    @field:Column(nullable = false)
    var uuid: String? = null
)