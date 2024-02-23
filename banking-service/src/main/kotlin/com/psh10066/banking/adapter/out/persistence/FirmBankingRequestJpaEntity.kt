package com.psh10066.banking.adapter.out.persistence

import com.psh10066.common.type.BankName
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Comment("펌뱅킹 요청")
@Entity
@Table(name = "firm_banking_request")
class FirmBankingRequestJpaEntity(

    @field:Comment("펌뱅킹 요청 고유번호")
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var firmBankingRequestId: Long? = null,

    @field:Comment("출금 은행명")
    @field:Column(nullable = false)
    @field:Enumerated(EnumType.STRING)
    var fromBankName: BankName? = null,

    @field:Comment("출금 계좌번호")
    @field:Column(nullable = false)
    var fromBankAccountNumber: String? = null,

    @field:Comment("입금 은행명")
    @field:Column(nullable = false)
    @field:Enumerated(EnumType.STRING)
    var toBankName: BankName? = null,

    @field:Comment("입금 계좌번호")
    @field:Column(nullable = false)
    var toBankAccountNumber: String? = null,

    @field:Comment("금액")
    @field:Column(nullable = false)
    var moneyAmount: Long? = null,

    @field:Comment("펌뱅킹 상태")
    @field:Column(nullable = false)
    @field:Enumerated(EnumType.STRING)
    var firmBankingStatus: FirmBankingStatus = FirmBankingStatus.REQUESTED,

    @field:Comment("Transaction UUID")
    @field:Column(nullable = false)
    var uuid: String? = null
)