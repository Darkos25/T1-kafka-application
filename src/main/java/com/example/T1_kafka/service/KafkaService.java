package com.example.T1_kafka.service;


import com.example.T1_kafka.model.DTO.MetricDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@KafkaListener(id = "multiGroup", topics = {"metrics-topic"})
public class KafkaService {

    @Autowired
    private MetricService metricService;

    @KafkaHandler
    public void metricDTO (MetricDTO metricDTO){
        metricService.save(metricDTO);
        log.info("Save metric");
    }

    @KafkaHandler
    public void unknown(Object object){
        log.info("Received unknown {}", object);
    }
}
