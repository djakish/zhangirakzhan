package com.example.zhangirakzhan.aspect;


import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect {
    private final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @After("execution(* com.example.zhangirakzhan.service.UserService.createUser())")
    public void anyMethod() {
        LOGGER.info("User created");
    }
}
