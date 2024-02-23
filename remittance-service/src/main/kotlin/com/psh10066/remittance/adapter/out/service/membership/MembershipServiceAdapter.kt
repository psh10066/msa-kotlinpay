package com.psh10066.remittance.adapter.out.service.membership

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.psh10066.common.CommonHttpClient
import com.psh10066.common.annotation.ExternalSystemAdapter
import com.psh10066.remittance.application.port.out.membership.MembershipPort
import com.psh10066.remittance.application.port.out.membership.MembershipStatus
import org.springframework.beans.factory.annotation.Value

@ExternalSystemAdapter
class MembershipServiceAdapter(
    private val commonHttpClient: CommonHttpClient,

    @Value("\${service.membership.url}")
    private val membershipServiceUrl: String
) : MembershipPort {

    override fun getMembershipStatus(membershipId: Long): MembershipStatus? {

        val url = "$membershipServiceUrl/membership/$membershipId"

        val jsonResponse: String
        try {
            jsonResponse = commonHttpClient.sendGetRequest(url).body()
        } catch (e: Exception) {
            return null
        }

        // json Membership
        val mapper = ObjectMapper().registerKotlinModule()
        val membership = mapper.readValue(jsonResponse, Membership::class.java)

        return MembershipStatus(membershipId = membership.membershipId, isValid = membership.isValid)
    }
}