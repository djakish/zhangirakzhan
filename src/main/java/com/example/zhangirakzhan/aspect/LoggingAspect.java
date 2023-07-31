package com.example.zhangirakzhan.aspect;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void controller() {
    }
    @Pointcut("execution(* *.*(..))")
    protected void allMethod() {
    }
}
