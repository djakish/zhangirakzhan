package com.example.zhangirakzhan;

import com.example.zhangirakzhan.aspect.LoggingAspect;
import com.example.zhangirakzhan.entity.User;
import com.example.zhangirakzhan.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ZhangirakzhanApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZhangirakzhanApplication.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(ZhangirakzhanApplication.class, args);
        UserService srv = applicationContext.getBean(UserService.class);


        var email = srv.findEmailById(1L);
        LOGGER.info(email);
    }

}
