package com.example.zhangirakzhan;

import com.example.zhangirakzhan.entity.Order;
import com.example.zhangirakzhan.service.OrderSender;
import com.example.zhangirakzhan.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@Slf4j
@ComponentScan(basePackages = "com.example.*")
public class ZhangirakzhanApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZhangirakzhanApplication.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(ZhangirakzhanApplication.class, args);
        UserService srv = applicationContext.getBean(UserService.class);
        var user = srv.findByUsername("user1");

        OrderSender orderSender = applicationContext.getBean(OrderSender.class);

        var order = new Order();
        order.setId(2L);
        order.setQuantity(1.0);
        order.setUser(user);

        orderSender.send(order);
    }


    @Value("${app.sending.topic.name}")
    public String sendingTopic;

    @Value("${app.receiving.topic.name}")
    public String receivingTopic;

}
