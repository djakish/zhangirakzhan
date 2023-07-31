package com.example.zhangirakzhan.service;

import com.example.zhangirakzhan.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderReader {
    @Value("#{zhangirakzhanApplication.receivingTopic}")
    private String receivingTopicName;
    @KafkaListener(topics = "#{zhangirakzhanApplication.receivingTopic}",
            groupId = "${spring.kafka.consumer.group-id}",
            clientIdPrefix = "json",
            containerFactory = "kafkaListenerContainerFactory")
    public void consume(ConsumerRecord<String, Order> cr) {
        log.info("Processing order -> {}", cr.value());
    }
}
