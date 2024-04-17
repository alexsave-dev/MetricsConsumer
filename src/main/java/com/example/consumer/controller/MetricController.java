package com.example.consumer.controller;

import com.example.consumer.dto.MetricListResponseDto;
import com.example.consumer.dto.MetricResponseDto;
import com.example.consumer.dto.ResponseStatus;
import com.example.consumer.service.MetricService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricController {
    private final MetricService metricService;

    public MetricController(MetricService metricService) {
        this.metricService = metricService;
    }

    @GetMapping("/metrics")
    public ResponseEntity<MetricListResponseDto> getAllMetrics() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(MetricListResponseDto.builder()
                    .status(ResponseStatus.SUCCESS)
                    .metrics(metricService.getAllMetrics())
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(MetricListResponseDto.builder()
                    .status(ResponseStatus.ERROR)
                    .message(e.getMessage())
                    .build());
        }
    }

    @GetMapping("/metrics/{metricId}")
    public ResponseEntity<MetricResponseDto> getMetricById(@PathVariable(name = "metricId") Long metricId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(MetricResponseDto.builder()
                    .status(ResponseStatus.SUCCESS)
                    .metric(metricService.getMetricById(metricId))
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(MetricResponseDto.builder()
                    .status(ResponseStatus.ERROR)
                    .message(e.getMessage())
                    .build());
        }
    }

}
