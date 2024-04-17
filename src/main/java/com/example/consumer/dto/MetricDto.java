package com.example.consumer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetricDto {
    private Long id;
    private BigDecimal processorUsagePercent;
    private BigDecimal memoryUsagePercent;
    private BigDecimal diskUsagePercent;
    private LocalDateTime createdAt;
}
