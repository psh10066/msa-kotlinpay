package com.psh10066.membership.application.service

import com.psh10066.common.annotation.UseCase
import com.psh10066.membership.adapter.out.persistence.MembershipMapper
import com.psh10066.membership.application.port.`in`.FindMembershipCommand
import com.psh10066.membership.application.port.`in`.FindMembershipUseCase
import com.psh10066.membership.application.port.out.FindMembershipPort
import com.psh10066.membership.domain.Membership
import org.springframework.transaction.annotation.Transactional

@UseCase
@Transactional
class FindMembershipService(
    private val findMembershipPort: FindMembershipPort,
    private val membershipMapper: MembershipMapper
) : FindMembershipUseCase {
    override fun findMembership(command: FindMembershipCommand): Membership {
        val jpaEntity = findMembershipPort.findMembership(command.membershipId)
        return membershipMapper.mapToDomainEntity(jpaEntity);
    }
}