package com.psh10066.money.adapter.axon.saga

import com.psh10066.common.event.*
import com.psh10066.common.type.BankName
import com.psh10066.money.adapter.axon.event.RechargingRequestCreatedEvent
import com.psh10066.money.application.port.out.IncreaseMoneyPort
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.modelling.saga.EndSaga
import org.axonframework.modelling.saga.SagaEventHandler
import org.axonframework.modelling.saga.SagaLifecycle
import org.axonframework.modelling.saga.StartSaga
import org.axonframework.spring.stereotype.Saga
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

@Saga
class MoneyRechargeSaga(

    @Transient
    private var commandGateway: CommandGateway? = null
) {
    @Autowired
    fun setCommandGateway(commandGateway: CommandGateway) {
        this.commandGateway = commandGateway
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "rechargingRequestId")
    fun handle(event: RechargingRequestCreatedEvent) {
        println("RechargingRequestCreatedEvent Start saga")

        val checkRegisteredBankAccountId = UUID.randomUUID().toString()
        SagaLifecycle.associateWith("checkRegisteredBankAccountId", checkRegisteredBankAccountId)

        // 기본적으로 Axon Framework에서 모든 aggregate의 변경은 aggregate 단위로 진행되어야 한다.
        commandGateway!!.send<String>(
            CheckRegisteredBankAccountCommand(
                aggregateIdentifier = event.registeredBankAccountAggregateIdentifier,
                rechargeRequestId = event.rechargingRequestId,
                membershipId = event.membershipId,
                checkRegisteredBankAccountId = checkRegisteredBankAccountId,
                bankName = event.bankName,
                bankAccountNumber = event.bankAccountNumber,
                amount = event.amount,
            )
        ).whenComplete { result, throwable ->
            if (throwable != null) {
                throwable.printStackTrace()
                println("CheckRegisteredBankAccountCommand Command failed")
            } else {
                println("CheckRegisteredBankAccountCommand Command success")
            }
        }
    }

    @SagaEventHandler(associationProperty = "checkRegisteredBankAccountId")
    fun handle(event: CheckedRegisteredBankAccountEvent) {
        println("CheckedRegisteredBankAccountEvent saga: $event")
        val status = event.isChecked
        if (status) {
            println("CheckedRegisteredBankAccountEvent event success")
        } else {
            println("CheckedRegisteredBankAccountEvent event failed")
        }

        val requestFirmBankingId = UUID.randomUUID().toString()
        SagaLifecycle.associateWith("requestFirmBankingId", requestFirmBankingId)

        commandGateway!!.send<String>(
            RequestFirmBankingCommand(
                requestFirmBankingId = requestFirmBankingId,
                aggregateIdentifier = event.firmBankingRequestAggregateIdentifier,
                rechargeRequestId = event.rechargingRequestId,
                membershipId = event.membershipId,
                fromBankName = event.fromBankName,
                fromBankAccountNumber = event.fromBankAccountNumber,
                toBankName = BankName.우리은행, // 시스템 법인 계좌
                toBankAccountNumber = "1010101010", // 시스템 법인 계좌
                moneyAmount = event.amount,
            )
        ).whenComplete { result, throwable ->
            if (throwable != null) {
                throwable.printStackTrace()
                println("CheckRegisteredBankAccountCommand Command failed")
            } else {
                println("CheckRegisteredBankAccountCommand Command success")
            }
        }
    }

    @SagaEventHandler(associationProperty = "requestFirmBankingId")
    fun handle(event: RequestFirmBankingFinishedEvent, increaseMoneyPort: IncreaseMoneyPort) {
        println("RequestFirmBankingFinishedEvent saga: $event")
        val status = event.status == 0
        if (status) {
            println("RequestFirmBankingFinishedEvent event success")
        } else {
            println("RequestFirmBankingFinishedEvent event failed")
        }

        val resultEntity =
            increaseMoneyPort.increaseMoney(membershipId = event.membershipId, moneyAmount = event.moneyAmount.toInt())

        if (resultEntity == null) {
            // 실패 시 보상 트랜잭션
            val rollbackFirmBankingId = UUID.randomUUID().toString()
            SagaLifecycle.associateWith("rollbackFirmBankingId", rollbackFirmBankingId)
            commandGateway!!.send<String>(
                RollbackFirmBankingRequestCommand(
                    rollbackFirmBankingId = rollbackFirmBankingId,
                    aggregateIdentifier = event.requestFirmBankingAggregateIdentifier,
                    rechargeRequestId = event.rechargingRequestId,
                    membershipId = event.membershipId,
                    fromBankName = event.toBankName, // 롤백이므로 반대로
                    fromBankAccountNumber = event.toBankAccountNumber,
                    toBankName = event.fromBankName,
                    toBankAccountNumber = event.fromBankAccountNumber,
                    amount = event.moneyAmount
                )
            )
        } else {
            // 성공 시 Saga 종료
            SagaLifecycle.end()
        }
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "rollbackFirmBankingId")
    fun handle(event: RollbackFirmBankingFinishedEvent) {
        println("RollbackFirmBankingFinishedEvent saga: $event")
    }
}