package com.psh10066.banking.adapter.`in`.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.psh10066.banking.adapter.out.external.bank.BankAccount
import com.psh10066.banking.domain.RegisteredBankAccount
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class RegisterBankAccountControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun testRegisterBankAccount() {
        val request = RegisterBankAccountRequest(
            membershipId = 1,
            bankName = "name",
            bankAccountNumber = "accountNumber",
            linkedStatusIsValid = true
        )
        val expect = RegisteredBankAccount(
            registeredBankAccountId = 1,
            membershipId = 1,
            bankName = "name",
            bankAccountNumber = "accountNumber",
            linkedStatusIsValid = true
        )

        mockMvc.perform(
            MockMvcRequestBuilders.post("/banking/account/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expect)))
    }
}