package com.psh10066.taskconsumer

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class TaskResultProducer(
    @Value("\${kafka.clusters.bootstrapservers}")
    private val bootstrapServers: String?,
    @Value("\${task.result.topic}")
    private val topic: String
) {
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

    // Kafka Cluster [key, value] Produce
    fun sendMessage(key: String, value: String) {
        val record = ProducerRecord(topic, key, value)
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