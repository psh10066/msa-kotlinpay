package com.psh10066.money.adapter.`in`.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.psh10066.common.*
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.*

@Component
class TaskResultConsumer(
    @Value("\${kafka.clusters.bootstrapservers}")
    private val bootstrapServers: String?,
    @Value("\${task.result.topic}")
    private val topic: String,

    private val loggingProducer: LoggingProducer,
    private val countDownLatchManager: CountDownLatchManager
) {
    private val consumer: KafkaConsumer<String, String>

    init {
        // Consumer Initialization
        val props = Properties()

        // kafka:29092
        props["bootstrap.servers"] = bootstrapServers

        // consumer group
        props["group.id"] = "my-group"

        // "key:value"
        props["key.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
        props["value.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"

        this.consumer = KafkaConsumer(props)
        consumer.subscribe(listOf(topic))

        val mapper = ObjectMapper().registerKotlinModule()

        val consumerThread = Thread {
            consumer.use {
                while (true) {
                    val records = it.poll(Duration.ofSeconds(1))
                    for (record in records) {
                        println("Received message : ${record.key()}/${record.value()}")

                        val task = mapper.readValue(record.value(), RechargingMoneyTask::class.java)

                        var taskResult = true
                        for (subTask in task.subTaskList) {
                            // TODO : validation
                            // 한 번만 실패해도 실패한 task로 간주
                            if (subTask.status == SubTaskStatus.FAIL) {
                                taskResult = false
                            }
                        }

                        if (taskResult) {
                            this.loggingProducer.sendMessage(task.taskID, "task success")
                            this.countDownLatchManager.setDataForKey(task.taskID, TaskResultStatus.SUCCESS)
                        } else {
                            this.loggingProducer.sendMessage(task.taskID, "task failed")
                            this.countDownLatchManager.setDataForKey(task.taskID, TaskResultStatus.FAIL)
                        }

                        // 3초 소요 가정
                        Thread.sleep(3000)

                        this.countDownLatchManager.getCountDownLatch(task.taskID)?.countDown()
                    }
                }
            }
        }
        consumerThread.start()
    }
}