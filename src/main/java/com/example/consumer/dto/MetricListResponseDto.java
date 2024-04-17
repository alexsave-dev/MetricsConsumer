package com.example.consumer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetricListResponseDto {
    private ResponseStatus status;
    private List<MetricDto> metrics;
    private String message;
}
