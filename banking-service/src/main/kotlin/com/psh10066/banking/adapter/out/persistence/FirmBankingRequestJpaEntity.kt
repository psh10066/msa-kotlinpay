package com.psh10066.banking.adapter.out.persistence

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "firm_banking_request")
class FirmBankingRequestJpaEntity(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var firmBankingRequestId: Long? = null,

    var fromBankName: String? = null,

    var fromBankAccountNumber: String? = null,

    var toBankName: String? = null,

    var toBankAccountNumber: String? = null,

    var moneyAmount: Long? = null,

    @field:Enumerated(EnumType.STRING)
    var firmBankingStatus: FirmBankingStatus = FirmBankingStatus.REQUESTED,

    var uuid: String? = null
) {
    constructor(
        fromBankName: String?,
        fromBankAccountNumber: String?,
        toBankName: String?,
        toBankAccountNumber: String?,
        moneyAmount: Long?,
        firmBankingStatus: FirmBankingStatus,
        uuid: UUID?
    ) : this() {
        this.fromBankName = fromBankName
        this.fromBankAccountNumber = fromBankAccountNumber
        this.toBankName = toBankName
        this.toBankAccountNumber = toBankAccountNumber
        this.moneyAmount = moneyAmount
        this.firmBankingStatus = firmBankingStatus
        this.uuid = uuid?.toString()
    }
}