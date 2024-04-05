package com.psh10066.money.application.service

import com.psh10066.common.CountDownLatchManager
import com.psh10066.common.annotation.UseCase
import com.psh10066.common.type.*
import com.psh10066.money.adapter.axon.command.MemberMoneyCreatedCommand
import com.psh10066.money.adapter.out.persistence.MoneyChangingRequestMapper
import com.psh10066.money.application.port.`in`.CreateMemberMoneyCommand
import com.psh10066.money.application.port.`in`.CreateMemberMoneyUseCase
import com.psh10066.money.application.port.`in`.IncreaseMoneyRequestCommand
import com.psh10066.money.application.port.`in`.IncreaseMoneyRequestUseCase
import com.psh10066.money.application.port.out.CreateMemberMoneyPort
import com.psh10066.money.application.port.out.IncreaseMoneyPort
import com.psh10066.money.application.port.out.SendRechargingMoneyTaskPort
import com.psh10066.money.domain.MoneyChangingRequest
import com.psh10066.money.domain.MoneyChangingStatus
import com.psh10066.money.domain.MoneyChangingType
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.transaction.annotation.Transactional
import java.util.*

@UseCase
@Transactional
class IncreaseMoneyRequestService(
    private val countDownLatchManager: CountDownLatchManager,
    private val sendRechargingMoneyTaskPort: SendRechargingMoneyTaskPort,
    private val increaseMoneyPort: IncreaseMoneyPort,
    private val moneyChangingRequestMapper: MoneyChangingRequestMapper,
    private val commandGateway: CommandGateway,
    private val createMemberMoneyPort: CreateMemberMoneyPort
) : IncreaseMoneyRequestUseCase, CreateMemberMoneyUseCase {
    override fun increaseMoneyRequest(command: IncreaseMoneyRequestCommand): MoneyChangingRequest {

        increaseMoneyPort.increaseMoney(
            membershipId = command.targetMembershipId,
            moneyAmount = command.amount
        )?.let {
            // 성공
            return moneyChangingRequestMapper.mapToDomainEntity(
                increaseMoneyPort.createMoneyChangingRequest(
                    targetMemberId = command.targetMembershipId,
                    moneyChangingType = MoneyChangingType.INCREASING,
                    changingMoneyAccount = command.amount,
                    moneyChangingStatus = MoneyChangingStatus.SUCCEEDED,
                )
            )
        }

        // 실패
        return moneyChangingRequestMapper.mapToDomainEntity(
            increaseMoneyPort.createMoneyChangingRequest(
                targetMemberId = command.targetMembershipId,
                moneyChangingType = MoneyChangingType.INCREASING,
                changingMoneyAccount = command.amount,
                moneyChangingStatus = MoneyChangingStatus.FAILED,
            )
        )
    }

    override fun increaseMoneyRequestAsync(command: IncreaseMoneyRequestCommand): MoneyChangingRequest {

        // 1. Subtask, Task
        val validMemberTask = SubTask(
            membershipId = command.targetMembershipId,
            subTaskName = "validMemberTask : 멤버십 유효성 검사",
            taskType = "membership",
            status = SubTaskStatus.READY
        )

        val validBankingAccountTask = SubTask(
            membershipId = command.targetMembershipId,
            subTaskName = "validBankingAccountTask : 뱅킹 계좌 유효성 검사",
            taskType = "banking",
            status = SubTaskStatus.READY
        )

        val subTaskList = listOf(validMemberTask, validBankingAccountTask)

        val task = RechargingMoneyTask(
            taskID = UUID.randomUUID().toString(),
            taskName = "Increase Money Task / 머니 충전 Task",
            membershipID = command.targetMembershipId,
            subTaskList = subTaskList,
            toBankName = BankName.국민은행,
            moneyAmount = command.amount
        )

        // 2. kafka produce
        sendRechargingMoneyTaskPort.sendRechargingMoneyTaskPort(task)
        countDownLatchManager.addCountDownLatch(task.taskID)

        // 3. Wait
        countDownLatchManager.getCountDownLatch(task.taskID)?.await()

        // 4. task result consume
        countDownLatchManager.getDataForKey(task.taskID)?.let {
            if (it == TaskResultStatus.SUCCESS) {
                // 4-1. consume ok, logic
                increaseMoneyPort.increaseMoney(
                    membershipId = command.targetMembershipId,
                    moneyAmount = command.amount
                ).let {
                    // 성공
                    return moneyChangingRequestMapper.mapToDomainEntity(
                        increaseMoneyPort.createMoneyChangingRequest(
                            targetMemberId = command.targetMembershipId,
                            moneyChangingType = MoneyChangingType.INCREASING,
                            changingMoneyAccount = command.amount,
                            moneyChangingStatus = MoneyChangingStatus.SUCCEEDED,
                        )
                    )
                }
            }
        }
        throw RuntimeException("증액 유효성 검사에 실패하였습니다.")
    }

    override fun createMemberMoney(command: CreateMemberMoneyCommand) {
        val axonCommand = MemberMoneyCreatedCommand(membershipId = command.membershipId)
        commandGateway.send<String>(axonCommand).whenComplete { result, throwable ->
            if (throwable != null) {
                println("throwable : $throwable")
                throw RuntimeException(throwable)
            } else {
                println("result : $result")
                createMemberMoneyPort.createMemberMoney(
                    membershipId = command.membershipId,
                    aggregateIdentifier = result
                )
            }
        }
    }
}