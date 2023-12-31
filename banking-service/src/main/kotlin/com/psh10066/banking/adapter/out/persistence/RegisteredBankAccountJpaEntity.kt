package com.psh10066.banking.adapter.out.persistence

import jakarta.persistence.*

@Entity
@Table(name = "registered_bank_account")
class RegisteredBankAccountJpaEntity(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val registeredBankAccountId: Long? = null,

    var membershipId: Long? = null,

    var bankName: String? = null,

    var bankAccountNumber: String? = null,

    var linkedStatusIsValid: Boolean? = null

)