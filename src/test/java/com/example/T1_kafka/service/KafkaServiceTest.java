package com.example.T1_kafka.service;

import com.example.T1_kafka.model.DTO.MetricDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringJUnitConfig
@EmbeddedKafka(partitions = 1, topics = {KafkaServiceTest.METRICS_TOPIC})
class KafkaServiceTest {

    static final String METRICS_TOPIC = "metrics-topic";

    @Mock
    private MetricService metricService;

    @InjectMocks
    private KafkaService kafkaService;

    @Test
    public void testMetricDTOHandler() {
        MetricDTO metricDTO = new MetricDTO();
        metricDTO.setName("Test Metric");

        kafkaService.metricDTO(metricDTO);

        verify(metricService).save(metricDTO);
    }

    @Test
    public void testUnknownHandler() {
        Object unknownObject = new Object();

        kafkaService.unknown(unknownObject);
    }
}