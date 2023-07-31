package com.example.zhangirakzhan.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
    @Around("execution(* com.example..*(..)))")
    public void anyMethod(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        LOGGER.info(new StringBuilder().append("EXECUTED").append(methodSignature.getDeclaringType().getSimpleName()).append(".").append(methodSignature.getName()).toString());
    }
}
