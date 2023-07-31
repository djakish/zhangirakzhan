package com.example.zhangirakzhan;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@Slf4j
public class ZhangirakzhanApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZhangirakzhanApplication.class, args);
    }

    @Value("${app.sending.topic.name}")
    public String sendingTopic;

    @Value("${app.receiving.topic.name}")
    public String receivingTopic;

}
