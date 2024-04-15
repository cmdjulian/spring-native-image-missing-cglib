package com.example.demo

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Lazy
import org.springframework.validation.annotation.Validated

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}

@SpringBootApplication
@Import(MqttSubscriberCollector::class)
@EnableConfigurationProperties(MqttProperties::class)
class DemoApplication

@Validated
@ConfigurationProperties(prefix = "mqtt")
data class MqttProperties(@get:NotEmpty val host: String = "", @get:Min(1) @get:Max(65535) val port: Int = 0)

class MqttSubscriberCollector(@Lazy private val config: MqttProperties) : BeanPostProcessor {
    private val logger = LoggerFactory.getLogger(javaClass)

    init {
        logger.info("MqttSubscriberCollector initialized with config: $config")
    }
}
