package com.psh10066.membership.application.service

import com.fastcampuspay.membership.domain.Membership
import com.psh10066.common.UseCase
import com.psh10066.membership.adapter.out.persistence.MembershipMapper
import com.psh10066.membership.application.port.`in`.RegisterMembershipCommand
import com.psh10066.membership.application.port.`in`.RegisterMembershipUseCase
import com.psh10066.membership.application.port.out.RegisterMembershipPort
import org.springframework.transaction.annotation.Transactional

@UseCase
@Transactional
class RegisterMembershipService(
    private val registerMembershipPort: RegisterMembershipPort,
    private val membershipMapper: MembershipMapper
) : RegisterMembershipUseCase {
    override fun registerMembership(command: RegisterMembershipCommand): Membership {

        val jpaEntity = registerMembershipPort.createMembership(
            name = command.name,
            email = command.email,
            address = command.address,
            isValid = command.isValid,
            isCorp = command.isCorp
        );

        return membershipMapper.mapToDomainEntity(jpaEntity)
    }
}