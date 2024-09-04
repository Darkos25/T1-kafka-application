package com.example.T1_kafka.config;

import com.example.T1_kafka.model.DTO.MetricDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * KafkaConsumerConfig is a configuration class for setting up the Kafka consumer.
 * This class defines the necessary beans for consuming messages from a Kafka topic
 * and converting them into MetricDTO objects.
 */
@Configuration
public class KafkaConsumerConfig {

    /**
     * KafkaConsumerConfig is a configuration class for setting up the Kafka consumer.
     * This class defines the necessary beans for consuming messages from a Kafka topic
     * and converting them into MetricDTO objects.
     */
    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * The group ID for the Kafka consumer.
     * This ID is used to identify the consumer group that the consumer belongs to.
     */
    @Value("${kafka.group-id}")
    private String groupId;

    /**
     * Creates a ConsumerFactory that configures the Kafka consumer with the necessary properties,
     * including the deserializer for the MetricDTO objects.
     *
     * @return a ConsumerFactory configured for consuming MetricDTO messages.
     */
    @Bean
    public ConsumerFactory<String, MetricDTO> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(props);
    }

    /**
     * Creates a ConcurrentKafkaListenerContainerFactory that is used for configuring Kafka listener containers.
     * This factory is used to configure and create Kafka listener containers for consuming messages.
     *
     * @return a ConcurrentKafkaListenerContainerFactory configured for MetricDTO messages.
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MetricDTO> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MetricDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
