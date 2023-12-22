package com.psh10066.membership.adapter.`in`.web

import com.fastcampuspay.membership.domain.Membership
import com.psh10066.common.WebAdapter
import com.psh10066.membership.application.port.`in`.RegisterMembershipCommand
import com.psh10066.membership.application.port.`in`.RegisterMembershipUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class RegisterMembershipController(
    private val registerMembershipUseCase: RegisterMembershipUseCase
) {

    @PostMapping(path = ["/membership/register"])
    fun registerMembership(@RequestBody request: RegisterMembershipRequest): Membership {

        val command = RegisterMembershipCommand(
            name = request.name,
            address = request.address,
            email = request.email,
            isValid = true,
            isCorp = request.isCorp
        )

        return registerMembershipUseCase.registerMembership(command)
    }
}