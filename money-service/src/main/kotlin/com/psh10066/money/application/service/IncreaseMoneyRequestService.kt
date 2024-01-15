package com.psh10066.money.application.service

import com.psh10066.common.CountDownLatchManager
import com.psh10066.common.RechargingMoneyTask
import com.psh10066.common.SubTask
import com.psh10066.common.UseCase
import com.psh10066.money.adapter.out.persistence.MoneyChangingRequestMapper
import com.psh10066.money.application.port.`in`.IncreaseMoneyRequestCommand
import com.psh10066.money.application.port.`in`.IncreaseMoneyRequestUseCase
import com.psh10066.money.application.port.out.IncreaseMoneyPort
import com.psh10066.money.application.port.out.SendRechargingMoneyTaskPort
import com.psh10066.money.domain.MoneyChangingRequest
import com.psh10066.money.domain.MoneyChangingStatus
import com.psh10066.money.domain.MoneyChangingType
import org.springframework.transaction.annotation.Transactional
import java.util.*

@UseCase
@Transactional
class IncreaseMoneyRequestService(
    private val countDownLatchManager: CountDownLatchManager,
    private val sendRechargingMoneyTaskPort: SendRechargingMoneyTaskPort,
    private val increaseMoneyPort: IncreaseMoneyPort,
    private val moneyChangingRequestMapper: MoneyChangingRequestMapper
) : IncreaseMoneyRequestUseCase {
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
            status = "ready"
        )

        val validBankingAccountTask = SubTask(
            membershipId = command.targetMembershipId,
            subTaskName = "validBankingAccountTask : 뱅킹 계좌 유효성 검사",
            taskType = "banking",
            status = "ready"
        )

        val subTaskList = listOf(validMemberTask, validBankingAccountTask)

        val task = RechargingMoneyTask(
            taskID = UUID.randomUUID().toString(),
            taskName = "Increase Money Task / 머니 충전 Task",
            membershipID = command.targetMembershipId,
            subTaskList = subTaskList,
            toBankName = "Bank Name",
            moneyAmount = command.amount
        )

        // 2. kafka produce
        sendRechargingMoneyTaskPort.sendRechargingMoneyTaskPort(task)
        countDownLatchManager.addCountDownLatch(task.taskID)

        // 3. Wait
        countDownLatchManager.getCountDownLatch(task.taskID)?.await()

        // 4. task result consume
        countDownLatchManager.getDataForKey(task.taskID)?.let {
            if (it.equals("success")) {
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
}