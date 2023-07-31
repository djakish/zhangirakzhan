package com.example.zhangirakzhan.controller;

import com.example.zhangirakzhan.entity.Order;
import com.example.zhangirakzhan.service.OrderSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/kafka")
public class KafkaController {

    private final OrderSender sender;
    @PostMapping(value = "/send")
    public void sendMessageToKafkaTopic(@RequestBody Order order) {
        this.sender.send(order);
    }
}
