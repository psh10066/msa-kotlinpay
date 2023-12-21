package com.psh10066.membership.adapter.`in`.web

import com.fastcampuspay.membership.domain.Membership
import com.psh10066.membership.application.port.`in`.FindMembershipCommand
import com.psh10066.membership.application.port.`in`.FindMembershipUseCase
import com.psh10066.membership.common.WebAdapter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class FindMembershipController(
    private val findMembershipUseCase: FindMembershipUseCase
) {

    @GetMapping(path = ["/membership/{membershipId}"])
    fun findMembershipByMemberId(@PathVariable membershipId: Long): Membership {

        val command = FindMembershipCommand(
            membershipId = membershipId
        )

        return findMembershipUseCase.findMembership(command);
    }
}