package com.example.T1_kafka.api;

import com.example.T1_kafka.model.DTO.MetricDTO;
import com.example.T1_kafka.service.MetricService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/metrics")
@RequiredArgsConstructor
@Tag(name = "API configuration", description = " API for create and get metrics")
public class MetricController {

    @Autowired
    private final MetricService metricService;

    @PostMapping
    @Operation(summary = "Create metric", description = "This method creates and sends metrics via Kafka and stores it in the DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Metric создан"),
            @ApiResponse(responseCode = "400", description = "Неверный запрос")
    })
    public ResponseEntity<String> createMetric(@RequestBody MetricDTO metricDTO) {
        metricService.sendMetric(metricDTO);
        return new ResponseEntity<>("Metric sent successfully", HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all metrics", description = "This method return all metrics")
    public ResponseEntity<List<MetricDTO>> getAllMetrics() {
        List<MetricDTO> metrics = metricService.getAllMetrics();
        return ResponseEntity.ok(metrics);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get metric by id", description = "This method return metric by id")
    public ResponseEntity<MetricDTO> getMetricById(@PathVariable Long id) {
        Optional<MetricDTO> metric = Optional.ofNullable(metricService.getMetricById(id));
        return metric.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
