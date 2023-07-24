package com.example.zhangirakzhan.service;


import com.example.zhangirakzhan.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderReceiver {
    @JmsListener(destination = "${spring.artemis.embedded.queues}")
    public void receive(Order order) {
        log.info("RECEIVING ORDER = '{}'", order);
    }
}
