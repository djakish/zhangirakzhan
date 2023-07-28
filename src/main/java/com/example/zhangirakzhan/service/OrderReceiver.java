package com.example.zhangirakzhan.service;


import com.example.zhangirakzhan.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static com.example.zhangirakzhan.config.JmsConfig.ORDER_QUEUE;

@Service
@Slf4j
public class OrderReceiver {
    @JmsListener(destination = ORDER_QUEUE)
    public void receive(Order order) {
        log.info("RECEIVING ORDER = '{}'", order);
    }
}
