package com.example.consumer.kafka;

import com.example.consumer.dto.MetricRequestDto;
import com.example.consumer.model.Metric;
import com.example.consumer.repository.MetricRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Transactional
@Slf4j
public class KafkaConsumer {

    private final MetricRepository metricRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public KafkaConsumer(MetricRepository metricRepository) {
        this.metricRepository = metricRepository;

    }

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "metrics")
    public void listener(ConsumerRecord<String, String> record) {
        try {
            MetricRequestDto request = objectMapper.readValue(record.value(), MetricRequestDto.class);

            metricRepository.save(Metric.builder()
                    .diskUsagePercent(request.getDiskUsagePercent())
                    .memoryUsagePercent(request.getMemoryUsagePercent())
                    .processorUsagePercent(request.getProcessorUsagePercent())
                    .createdAt(LocalDateTime.now())
                    .build());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
