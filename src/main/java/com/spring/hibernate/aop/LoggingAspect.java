package com.spring.hibernate.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    // pointcut declarations
    @Pointcut("execution(* com.spring.hibernate.apis.*.*(..))")
    private void forApiPackage() {}

    @Pointcut("execution(* com.spring.hibernate.services.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("forApiPackage() || forServicePackage()")
    private void forApiFlow() {}

    @Before("forApiFlow()")
    public void before(JoinPoint joinPoint) {

        // display method signature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.info("@Before Calling Method: " + signature);

        // display method arguments
        Object[] args = joinPoint.getArgs();
        for(Object obj: args){
            log.info("@Before Method Arguments: " + obj.toString());
        }
    }

    @AfterReturning(pointcut = "forApiFlow()", returning = "result") // Pointcut Expression
    public void loggingAllAfterReturningAdvice(JoinPoint joinPoint, Object result) {

        // display method signature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.info("@AfterReturning Calling Method: " + signature);

        // display method return
        log.info("@AfterReturning Method Return: " + result);

    }

}
