package com.example.T1_kafka.model;

import com.example.T1_kafka.model.DTO.MetricDTO;
import com.example.T1_kafka.service.MetricService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MetricMapper {

    private MetricService metricService;

    public static MetricDTO toDTO(Metric metric) {
        MetricDTO dto = new MetricDTO();
        dto.setId(metric.getId());
        dto.setType(metric.getType());
        dto.setName(metric.getName());
        dto.setDescription(metric.getDescription());
        return dto;
    }

    public static Metric toEntity(MetricDTO dto) {
        Metric metric = new Metric();
        metric.setName(dto.getName());
        metric.setType(dto.getType());
        metric.setDescription(dto.getDescription());
        return metric;
    }

}
