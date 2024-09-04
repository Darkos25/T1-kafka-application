package com.example.T1_kafka.model.DTO;

import com.example.T1_kafka.model.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for metric")
public class MetricDTO {

    @Schema(description = "metric id")
    private Long id;
    @Schema(description = "metric name", example = "CPU Usage")
    private String name;
    @Schema(description = "metric type, can be only PERFORMANCE, RESOURCE, ERROR", example = "RESOURCE")
    private Type type;
    @Schema(description = "metric description", example = "CPU usage over 90%")
    private String description;
}
