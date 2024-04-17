package com.example.consumer.service;

import com.example.consumer.dto.MetricDto;
import com.example.consumer.service.exception.MetricServiceException;

import java.util.List;

public interface MetricService {

    List<MetricDto> getAllMetrics();
    MetricDto getMetricById(Long metricId) throws MetricServiceException;
}
