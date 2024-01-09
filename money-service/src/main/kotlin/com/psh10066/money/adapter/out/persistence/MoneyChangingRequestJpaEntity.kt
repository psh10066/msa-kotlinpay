package com.psh10066.money.adapter.out.persistence

import com.psh10066.money.domain.MoneyChangingStatus
import com.psh10066.money.domain.MoneyChangingType
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "money_changing_request")
class MoneyChangingRequestJpaEntity(

    @field:Id
    @field:GeneratedValue
    val moneyChangingRequestId: Long? = null,

    var targetMembershipId: Long? = null,

    @field:Enumerated(value = EnumType.STRING)
    var moneyChangingType: MoneyChangingType? = null,

    var moneyAmount: Int? = null,

    @field:Temporal(TemporalType.TIMESTAMP)
    var timestamp: LocalDateTime? = null,

    @field:Enumerated(value = EnumType.STRING)
    var moneyChangingStatus: MoneyChangingStatus? = null,

    var uuid: String? = null
) {
    constructor(
        targetMembershipId: Long?,
        moneyChangingType: MoneyChangingType?,
        moneyAmount: Int?,
        timestamp: LocalDateTime?,
        moneyChangingStatus: MoneyChangingStatus?,
        uuid: UUID?
    ) : this() {
        this.targetMembershipId = targetMembershipId
        this.moneyChangingType = moneyChangingType
        this.moneyAmount = moneyAmount
        this.timestamp = timestamp
        this.moneyChangingStatus = moneyChangingStatus
        this.uuid = uuid?.toString()
    }
}