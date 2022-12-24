package com.spring.hibernate.aopdemo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect // Aspect is a Java Class with Collections of Advices
@Component
@Slf4j
@Order(1)
public class MySecurityAspect {

//    @Before("execution(public void addAccount())") // Pointcut Expression
//    public void loggingAddAccountAdvice() {
//        log.info("Executing Logging with @Before advice on addAccount()");
//    }
//
//    @Before("execution(public void addAccount())") // Pointcut Expression
//    public void securityAddAccountAdvice() {
//        log.info("Executing Security with @Before advice on addAccount()");
//    }


    /**
     * Instead of creating Pointcut Expression copy and paste one for logging, one for security
     * We can make use of Pointcut Declaration
     */

//    @Pointcut("execution(public void com.spring.hibernate.aop.*.*(..))")
//    private void forDaoPackage() {}
//
//    @Before("forDaoPackage()") // Pointcut Expression
//    public void loggingAllBeforeAdvice() {
//        log.info("Executing Logging with @Before advice on all method(any num of argument)");
//    }
//
//    @Before("forDaoPackage()") // Pointcut Expression
//    public void securityAllBeforeAdvice() {
//        log.info("Executing Security with @Before advice on all method(any num of argument))");
//    }
//
//    @After("forDaoPackage()") // Pointcut Expression
//    public void loggingAllAfterAdvice() {
//        log.info("Executing Logging with @After advice on all method(any num of argument))");
//    }
//
//    @After("forDaoPackage()") // Pointcut Expression
//    public void securityAllAfterAdvice() {
//        log.info("Executing Security with @After advice on all method(any num of argument))");
//    }


    /**
     * Pointcut Expression
     *
     *  @Before("expressionOne() && expressionTwo()")
     *  @Before("expressionOne() || expressionTwo()")
     *  @Before("expressionOne() && !expressionTwo()")
     *
     *  Example all methods in a package except getter & setter
     */

//    @Pointcut("execution(public void com.spring.hibernate.aop.*.*(..))")
//    private void forDaoPackage() {}
//
//    @Pointcut("execution(public void com.spring.hibernate.aop.*.get*(..))")
//    private void getter() {}
//
//    @Pointcut("execution(public void com.spring.hibernate.aop.*.set*(..))")
//    private void setter() {}
//
//    @Pointcut("forDaoPackage() && !(getter() || setter())")
//    private void forDaoPackageNoGetterSetter() {}
//
//    @Before("forDaoPackageNoGetterSetter()") // Pointcut Expression
//    public void securityAllBeforeAdvice() {
//        log.info("Executing Security with @Before advice on all method(any num of argument))");
//    }
//
//    @After("forDaoPackageNoGetterSetter()") // Pointcut Expression
//    public void securityAllAfterAdvice() {
//        log.info("Executing Security with @After advice on all method(any num of argument))");
//    }

    /**
     * To order Aspect like first security will run then logging we need to refactor
     * We need to create separate Aspect like SecurityAspect & LoggingAspect
     * and use @Order Annotation
     *
     * @Order(1) or @Order(2): Lower number has higher precedence
     * Ranger of order Integer.MIN_VALUE to Integer.MAX_VALUE
     * Negative numbers are allowed
     * Does not have to be consecutive
     * If Order has same number than they can run in any order same like if we didn't give order
     */

    /**
     * To get information about Method Signature & Method Arguments
     * We need to pass  JoinPoint as an argument to the advice
     */

    @Pointcut("execution(public void com.spring.hibernate.aopdemo.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut("execution(public void com.spring.hibernate.aopdemo.*.get*(..))")
    private void getter() {}

    @Pointcut("execution(public void com.spring.hibernate.aopdemo.*.set*(..))")
    private void setter() {}

    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoPackageNoGetterSetter() {}


    @Before("forDaoPackageNoGetterSetter()") // Pointcut Expression
    public void securityAllBeforeAdvice(JoinPoint joinPoint) {
        // display method signature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.info("Method: " + signature);

        // display method arguments
        Object[] args = joinPoint.getArgs();
        for(Object obj: args){
            log.info(obj.toString());
        }

        log.info("Executing Security with @Before advice on all method(any num of argument))");
    }

    @After("forDaoPackageNoGetterSetter()") // Pointcut Expression
    public void securityAllAfterAdvice(JoinPoint joinPoint) {
        // display method signature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.info("Method: " + signature);

        // display method arguments
        Object[] args = joinPoint.getArgs();
        for(Object obj: args){
            log.info(obj.toString());
        }

        log.info("Executing Security with @After advice on all method(any num of argument))");
    }


}
