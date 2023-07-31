package com.example.zhangirakzhan.service;

import com.example.zhangirakzhan.entity.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderSender {
    @Value("#{zhangirakzhanApplication.sendingTopic}")
    public String sendingToTopicName;
    @Value("#{zhangirakzhanApplication.receivingTopic}")
    private String sender;
    private final KafkaTemplate<String,Object> kafkaTemplate;
    public void send(Order order) {
        log.info("[{}] Sending order {}", sender, order);
        kafkaTemplate.send(sendingToTopicName, UUID.randomUUID().toString(), order);
    }
}
