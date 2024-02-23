package com.psh10066.membership.application.service

import com.psh10066.common.annotation.UseCase
import com.psh10066.membership.adapter.out.persistence.MembershipMapper
import com.psh10066.membership.application.port.`in`.ModifyMembershipCommand
import com.psh10066.membership.application.port.`in`.ModifyMembershipUseCase
import com.psh10066.membership.application.port.out.ModifyMembershipPort
import com.psh10066.membership.domain.Membership
import org.springframework.transaction.annotation.Transactional

@UseCase
@Transactional
class ModifyMembershipService(
    private val modifyMembershipPort: ModifyMembershipPort,
    private val membershipMapper: MembershipMapper
) : ModifyMembershipUseCase {
    override fun modifyMembership(command: ModifyMembershipCommand): Membership {

        val jpaEntity = modifyMembershipPort.modifyMembership(
            membershipId = command.membershipId,
            name = command.name,
            email = command.email,
            address = command.address,
            isValid = command.isValid,
            isCorp = command.isCorp
        );

        return membershipMapper.mapToDomainEntity(jpaEntity)
    }
}