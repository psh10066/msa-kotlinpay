package com.psh10066.banking.adapter.out.persistence

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "firm_banking_request")
class FirmBankingRequestJpaEntity(

    @field:Id
    @field:GeneratedValue
    var firmBankingRequestId: Long? = null,

    var fromBankName: String? = null,

    var fromBankAccountNumber: String? = null,

    var toBankName: String? = null,

    var toBankAccountNumber: String? = null,

    var moneyAmount: Long? = null,

    var firmBankingStatus: Int = 0, // 0: 요청, 1: 성공, 2: 실패

    var uuid: String? = null
) {
    constructor(
        fromBankName: String?,
        fromBankAccountNumber: String?,
        toBankName: String?,
        toBankAccountNumber: String?,
        moneyAmount: Long?,
        firmBankingStatus: Int,
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