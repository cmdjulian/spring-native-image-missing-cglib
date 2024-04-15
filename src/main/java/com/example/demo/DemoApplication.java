package com.example.demo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
@Import(MqttSubscriberCollector.class)
@EnableConfigurationProperties(MqttProperties.class)
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

@Validated
@ConfigurationProperties(prefix = "mqtt")
class MqttProperties {
    @NotEmpty
    private String host = "";

    @Min(1)
    @Max(65535)
    private int port = 0;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "MqttProperties{" +
                "host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}

class MqttSubscriberCollector implements BeanPostProcessor {

    private final Logger logger = LoggerFactory.getLogger(MqttSubscriberCollector.class);

    private final MqttProperties config;

    public MqttSubscriberCollector(@Lazy MqttProperties config) {
        this.config = config;
        logger.info("MqttSubscriberCollector initialized with config: " + config.toString());
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
