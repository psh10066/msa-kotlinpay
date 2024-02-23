package com.psh10066.banking.adapter.out.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.psh10066.banking.application.port.out.GetMembershipPort
import com.psh10066.banking.application.port.out.Membership
import com.psh10066.common.CommonHttpClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class MembershipServiceAdapter(
    private val commonHttpClient: CommonHttpClient,

    @Value("\${service.membership.url}")
    private val membershipServiceUrl: String
) : GetMembershipPort {

    override fun getMembership(membershipId: Long): MembershipStatus? {

        val url = "$membershipServiceUrl/membership/$membershipId"

        val jsonResponse = commonHttpClient.sendGetRequest(url).body()

        // json Membership
        val mapper = ObjectMapper().registerKotlinModule()
        val membership = mapper.readValue(jsonResponse, Membership::class.java)

        return MembershipStatus(membershipId = membership.membershipId, isValid = membership.isValid)
    }
}