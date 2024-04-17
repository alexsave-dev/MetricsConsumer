package com.example.consumer.service;

import com.example.consumer.dto.MetricDto;
import com.example.consumer.model.Metric;
import com.example.consumer.repository.MetricRepository;
import com.example.consumer.service.exception.MetricServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricServiceImpl implements MetricService {
    private final MetricRepository metricRepository;

    public MetricServiceImpl(MetricRepository metricRepository) {
        this.metricRepository = metricRepository;
    }

    @Override
    public List<MetricDto> getAllMetrics() {
        return metricRepository.findAll().stream().map(it -> MetricDto.builder()
                .id(it.getId())
                .createdAt(it.getCreatedAt())
                .diskUsagePercent(it.getDiskUsagePercent())
                .processorUsagePercent(it.getProcessorUsagePercent())
                .memoryUsagePercent(it.getMemoryUsagePercent())
                .build()).toList();
    }

    @Override
    public MetricDto getMetricById(Long metricId) throws MetricServiceException {
        Metric metric = metricRepository.findById(metricId)
                .orElseThrow(() -> new MetricServiceException(String.format("Metric with ID = %d not found", metricId)));
        return MetricDto.builder()
                .id(metric.getId())
                .createdAt(metric.getCreatedAt())
                .memoryUsagePercent(metric.getMemoryUsagePercent())
                .processorUsagePercent(metric.getProcessorUsagePercent())
                .diskUsagePercent(metric.getDiskUsagePercent())
                .build();
    }
}
