package com.psh10066.membership.application.port.`in`

import com.psh10066.common.SelfValidating
import jakarta.validation.constraints.NotNull

data class FindMembershipCommand(

    @field:NotNull
    val membershipId: Long

) : SelfValidating<FindMembershipCommand>()
