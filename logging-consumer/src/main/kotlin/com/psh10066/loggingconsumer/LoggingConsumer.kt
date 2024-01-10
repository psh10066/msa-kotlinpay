package com.psh10066.loggingconsumer

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.*

@Component
class LoggingConsumer(
    @Value("\${kafka.clusters.bootstrapservers}")
    private val bootstrapServers: String?,
    @Value("\${logging.topic}")
    private val topic: String
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

        val consumerThread = Thread {
            consumer.use {
                while (true) {
                    val records = it.poll(Duration.ofSeconds(1))
                    for (record in records) {
                        // print to Stdout
                        println("Received message: " + record.value())
                    }
                }
            }
        }
        consumerThread.start()
    }
}