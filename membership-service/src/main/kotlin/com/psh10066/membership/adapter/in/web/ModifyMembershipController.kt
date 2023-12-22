package com.psh10066.membership.adapter.`in`.web

import com.fastcampuspay.membership.domain.Membership
import com.psh10066.membership.application.port.`in`.ModifyMembershipCommand
import com.psh10066.membership.application.port.`in`.ModifyMembershipUseCase
import com.psh10066.membership.common.WebAdapter
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class ModifyMembershipController(
    private val modifyMembershipUseCase: ModifyMembershipUseCase
) {

    @PostMapping(path = ["/membership/modify"])
    fun modifyMembershipByMemberId(@RequestBody request: ModifyMembershipRequest): Membership {

        val command = ModifyMembershipCommand(
            membershipId = request.membershipId,
            name = request.name,
            address = request.address,
            email = request.email,
            isValid = request.isValid,
            isCorp = request.isCorp
        )

        return modifyMembershipUseCase.modifyMembership(command);
    }
}