package com.example.T1_kafka.service;

import com.example.T1_kafka.error.MetricNotFoundException;
import com.example.T1_kafka.model.DTO.MetricDTO;
import com.example.T1_kafka.model.Metric;
import com.example.T1_kafka.repository.MetricRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
@ExtendWith(MockitoExtension.class)
class MetricServiceTest {

    @Mock
    private MetricRepository metricRepository; // Мокаем репозиторий

    @Mock
    private KafkaTemplate<String, MetricDTO> kafkaTemplate; // Мокаем KafkaTemplate

    @InjectMocks
    private MetricService metricService; // Внедряем моки в сервис

    @Test
    void testSaveMetric() {
        MetricDTO metricDTO = new MetricDTO();
        Metric metric = new Metric();
        when(metricRepository.save(any(Metric.class))).thenReturn(metric);

        Metric savedMetric = metricService.save(metricDTO);

        assertNotNull(savedMetric);
        verify(metricRepository, times(1)).save(any(Metric.class));
    }

    @Test
    void testGetAllMetrics() {
        List<Metric> metrics = Arrays.asList(new Metric(), new Metric());
        when(metricRepository.findAll()).thenReturn(metrics);

        List<MetricDTO> metricDTOs = metricService.getAllMetrics();

        assertEquals(metrics.size(), metricDTOs.size());
        verify(metricRepository, times(1)).findAll();
    }

    @Test
    void testGetMetricByIdFound() {
        Long id = 1L;
        Metric metric = new Metric();
        when(metricRepository.findById(id)).thenReturn(Optional.of(metric));

        MetricDTO metricDTO = metricService.getMetricById(id);

        assertNotNull(metricDTO);
        verify(metricRepository, times(1)).findById(id);
    }

    @Test
    void testGetMetricByIdNotFound() {
        Long id = 1L;
        when(metricRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(MetricNotFoundException.class, () -> {
            metricService.getMetricById(id);
        });
        verify(metricRepository, times(1)).findById(id);
    }
}