package com.psh10066.banking.adapter.out.persistence

import com.psh10066.banking.application.port.out.RequestFirmBankingPort
import com.psh10066.common.BankName
import com.psh10066.common.PersistenceAdapter
import java.util.*

@PersistenceAdapter
class FirmBankingRequestPersistenceAdapter(
    val firmBankingRequestRepository: SpringDataFirmBankingRequestRepository
) : RequestFirmBankingPort {
    override fun createFirmBankingRequest(
        fromBankName: BankName,
        fromBankAccountNumber: String,
        toBankName: BankName,
        toBankAccountNumber: String,
        moneyAmount: Long,
        firmBankingStatus: FirmBankingStatus
    ): FirmBankingRequestJpaEntity {
        return firmBankingRequestRepository.save(
            FirmBankingRequestJpaEntity(
                fromBankName = fromBankName,
                fromBankAccountNumber = fromBankAccountNumber,
                toBankName = toBankName,
                toBankAccountNumber = toBankAccountNumber,
                moneyAmount = moneyAmount,
                firmBankingStatus = firmBankingStatus,
                uuid = UUID.randomUUID()
            )
        )
    }

    override fun modifyFirmBankingRequest(firmBankingRequestJpaEntity: FirmBankingRequestJpaEntity): FirmBankingRequestJpaEntity {
        return firmBankingRequestRepository.save(firmBankingRequestJpaEntity)
    }

}