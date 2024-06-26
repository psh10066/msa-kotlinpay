package com.psh10066.money.adapter.out.persistence

import com.psh10066.common.annotation.PersistenceAdapter
import com.psh10066.money.application.port.out.CreateMemberMoneyPort
import com.psh10066.money.application.port.out.GetMemberMoneyPort
import com.psh10066.money.application.port.out.IncreaseMoneyPort
import com.psh10066.money.domain.MoneyChangingStatus
import com.psh10066.money.domain.MoneyChangingType
import java.time.LocalDateTime
import java.util.*

@PersistenceAdapter
class MoneyChangingRequestPersistenceAdapter(
    val moneyChangingRequestRepository: SpringDataMoneyChangingRequestRepository,
    val memberMoneyRepository: SpringDataMemberMoneyRepository
) : IncreaseMoneyPort, CreateMemberMoneyPort, GetMemberMoneyPort {

    override fun createMoneyChangingRequest(
        targetMemberId: Long,
        moneyChangingType: MoneyChangingType,
        changingMoneyAccount: Int,
        moneyChangingStatus: MoneyChangingStatus,
    ): MoneyChangingRequestJpaEntity {
        return moneyChangingRequestRepository.save(
            MoneyChangingRequestJpaEntity(
                targetMembershipId = targetMemberId,
                moneyChangingType = moneyChangingType,
                moneyAmount = changingMoneyAccount,
                timestamp = LocalDateTime.now(),
                moneyChangingStatus = moneyChangingStatus,
                uuid = UUID.randomUUID().toString()
            )
        )
    }

    override fun increaseMoney(
        membershipId: Long,
        moneyAmount: Int
    ): MemberMoneyJpaEntity? {
        val entity = memberMoneyRepository.findByMembershipId(membershipId)
            ?: memberMoneyRepository.save(
                MemberMoneyJpaEntity(
                    membershipId = membershipId,
                    moneyBalance = 0,
                    aggregateIdentifier = ""
                )
            )

        entity.moneyBalance = entity.moneyBalance?.plus(moneyAmount)
        return memberMoneyRepository.save(entity)
    }

    override fun createMemberMoney(membershipId: Long, aggregateIdentifier: String) {
        val entity = MemberMoneyJpaEntity(
            membershipId = membershipId,
            moneyBalance = 0,
            aggregateIdentifier = aggregateIdentifier
        )
        memberMoneyRepository.save(entity)
    }

    override fun getMemberMoney(membershipId: Long): MemberMoneyJpaEntity {
        return memberMoneyRepository.findByMembershipId(membershipId)
            ?: memberMoneyRepository.save(
                MemberMoneyJpaEntity(
                    membershipId = membershipId,
                    moneyBalance = 0,
                    aggregateIdentifier = UUID.randomUUID().toString()
                )
            )
    }
}