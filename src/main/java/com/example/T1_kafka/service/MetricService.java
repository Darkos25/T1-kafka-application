package com.example.T1_kafka.service;

import com.example.T1_kafka.error.MetricNotFoundException;
import com.example.T1_kafka.model.DTO.MetricDTO;
import com.example.T1_kafka.model.Metric;
import com.example.T1_kafka.model.MetricMapper;
import com.example.T1_kafka.repository.MetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricService {

    @Autowired
    private final KafkaTemplate<String, MetricDTO> kafkaTemplate;

    private static final String TOPIC = "metrics-topic";

    @Autowired
    private MetricRepository metricRepository;

    public MetricService(KafkaTemplate<String, MetricDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Metric save (MetricDTO metricDTO){
        return metricRepository.save(MetricMapper.toEntity(metricDTO));
    }

    public void sendMetric(MetricDTO metricDTO) {
        kafkaTemplate.send(TOPIC, metricDTO);

    }

    public List<MetricDTO> getAllMetrics() {
        return metricRepository.findAll().stream()
                .map(MetricMapper::toDTO)
                .toList();
    }

    public MetricDTO getMetricById(Long id) {
        return metricRepository.findById(id)
                .map(MetricMapper::toDTO)
                .orElseThrow(() -> new MetricNotFoundException("Metric not found with id: " + id));
    }
}
