package com.psh10066.money.adapter.out.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.psh10066.common.RechargingMoneyTask
import com.psh10066.money.application.port.out.SendRechargingMoneyTaskPort
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class TaskProducer(
    @Value("\${kafka.clusters.bootstrapservers}")
    private val bootstrapServers: String?,
    @Value("\${task.topic}")
    private val topic: String
) : SendRechargingMoneyTaskPort {
    private val producer: KafkaProducer<String, String>

    init {
        // Producer Initialization
        val props = Properties()

        // kafka:29092
        props["bootstrap.servers"] = bootstrapServers

        // "key:value"
        props["key.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
        props["value.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"

        this.producer = KafkaProducer(props)
    }

    override fun sendRechargingMoneyTaskPort(task: RechargingMoneyTask) {
        this.sendMessage(task.taskID, task)
    }

    // Kafka Cluster [key, value] Produce
    fun sendMessage(key: String, value: RechargingMoneyTask) {
        val mapper = ObjectMapper().registerKotlinModule()

        val jsonStringToProduce = mapper.writeValueAsString(value)

        val record = ProducerRecord(topic, key, jsonStringToProduce)
        producer.send(record) { metadata: RecordMetadata, exception: Exception? ->
            if (exception == null) {
                // System.out.println("Message sent successfully. Offset: " + metadata.offset());
            } else {
                exception.printStackTrace()
                // System.err.println("Failed to send message: " + exception.getMessage());
            }
        }
    }
}