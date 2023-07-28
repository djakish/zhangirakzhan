package com.example.zhangirakzhan.service;


import com.example.zhangirakzhan.entity.Order;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static com.example.zhangirakzhan.config.JmsConfig.ORDER_QUEUE;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderSender {
    @Autowired
    private JmsTemplate jmsTemplate;

    @PostConstruct
    public void init(){
        jmsTemplate.setDeliveryDelay(2000L);
    }

    @Value(ORDER_QUEUE)
    private String queueName;

    public void send(Order order) {
        log.info("SENDING ORDER='{}'", order);
        jmsTemplate.convertAndSend(queueName, order);
    }

}
