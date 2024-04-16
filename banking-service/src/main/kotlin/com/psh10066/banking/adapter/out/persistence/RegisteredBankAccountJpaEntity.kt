package com.psh10066.banking.adapter.out.persistence

import com.psh10066.common.type.BankName
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Comment("등록된 계좌")
@Entity
@Table(
    name = "registered_bank_account",
    indexes = [
        Index(name = "membershipId", columnList = "membershipId"),
    ]
)
class RegisteredBankAccountJpaEntity(

    @field:Comment("등록된 계좌 고유번호")
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val registeredBankAccountId: Long? = null,

    @field:Comment("회원 고유번호")
    @field:Column(nullable = false)
    var membershipId: Long? = null,

    @field:Comment("은행 이름")
    @field:Column(nullable = false)
    var bankName: BankName? = null,

    @field:Comment("계좌번호")
    @field:Column(nullable = false)
    var bankAccountNumber: String? = null,

    @field:Comment("정상 연동 여부")
    @field:Column(nullable = false)
    var linkedStatusIsValid: Boolean? = null,

    @field:Comment("Transaction UUID")
    @field:Column(nullable = false)
    var aggregateIdentifier: String? = null
)