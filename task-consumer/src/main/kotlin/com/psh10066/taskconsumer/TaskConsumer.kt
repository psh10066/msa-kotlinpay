package com.psh10066.taskconsumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.psh10066.common.type.RechargingMoneyTask
import com.psh10066.common.type.SubTaskStatus
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.*

@Component
class TaskConsumer(
    @Value("\${kafka.clusters.bootstrapservers}")
    private val bootstrapServers: String?,
    @Value("\${task.topic}")
    private val topic: String,

    private val taskResultProducer: TaskResultProducer
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
                        // task run
                        val task = mapper.readValue(record.value(), RechargingMoneyTask::class.java)
                        // task result
                        for (subTask in task.subTaskList) {
                            // 모든 sub task가 이상이 없다고 가정
                            subTask.status = SubTaskStatus.SUCCESS
                        }
                        // produce task result
                        taskResultProducer.sendMessage(record.key(), record.value())
                    }
                }
            }
        }
        consumerThread.start()
    }
}