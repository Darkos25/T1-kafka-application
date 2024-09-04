package com.example.T1_kafka.config;

import com.example.T1_kafka.model.DTO.MetricDTO;
import com.example.T1_kafka.model.Metric;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * KafkaProducerConfig is a configuration class that sets up the Kafka producer.
 * This class defines the necessary beans for producing messages to a Kafka topic
 * and serializing them as MetricDTO objects.
 */
@Configuration
public class KafkaProducerConfig {

    /**
     * The bootstrap servers for Kafka.
     * This is a comma-separated list of host:port pairs used for establishing the initial connection to the Kafka cluster.
     */
    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * Creates a ProducerFactory that configures the Kafka producer with the necessary properties,
     * including the serializer for the MetricDTO objects.
     *
     * @return a ProducerFactory configured for producing MetricDTO messages.
     */
    @Bean
    public ProducerFactory<String, MetricDTO> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /**
     * Creates a KafkaTemplate for sending messages to Kafka.
     * The KafkaTemplate provides convenient methods for sending data to a Kafka topic.
     *
     * @return a KafkaTemplate configured for MetricDTO messages.
     */
    @Bean
    public KafkaTemplate<String, MetricDTO> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    /**
     * Creates a new Kafka topic named "metrics-topic".
     * The topic is created with 1 partition and a replication factor of 1.
     *
     * @return a NewTopic object representing the "metrics-topic".
     */
    @Bean
    public NewTopic metricsTopic() {
        return new NewTopic("metrics-topic", 1, (short) 1);
    }
}
