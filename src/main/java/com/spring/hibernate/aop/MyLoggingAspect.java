package com.spring.hibernate.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect // Aspect is a Java Class with Collections of Advices
@Component
@Slf4j
@Order(2)
public class MyLoggingAspect {

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
//    public void loggingAllBeforeAdvice() {
//        log.info("Executing Logging with @Before advice on all method(any num of argument)");
//    }
//
//    @After("forDaoPackageNoGetterSetter()") // Pointcut Expression
//    public void loggingAllAfterAdvice() {
//        log.info("Executing Logging with @After advice on all method(any num of argument))");
//    }


    /**
     * To order Aspect like first security will run then logging we need to refactor
     * We need to create separate Aspect like SecurityAspect & LoggingAspect
     * and use @Order Annotation
     *
     * @Order(1) or @Order(2): Lower number has higher precedence
     */


    /**
     * To get information about Method Signature & Method Arguments
     * We need to pass  JoinPoint as an argument to the advice
     */

    @Pointcut("execution(public void com.spring.hibernate.aop.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut("execution(public void com.spring.hibernate.aop.*.get*(..))")
    private void getter() {}

    @Pointcut("execution(public void com.spring.hibernate.aop.*.set*(..))")
    private void setter() {}

    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoPackageNoGetterSetter() {}


    @Before("forDaoPackageNoGetterSetter()") // Pointcut Expression
    public void loggingAllBeforeAdvice(JoinPoint joinPoint) {
        // display method signature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.info("Method: " + signature);

        // display method arguments
        Object[] args = joinPoint.getArgs();
        for(Object obj: args){
            log.info(obj.toString());
        }

        log.info("Executing Logging with @Before advice on all method(any num of argument))");
    }


    /**
     * AfterThrowing advice which intercepts the exception
     * We can lof the exception
     * We can perform audit on the exception
     * Notify devops team via email or sms
     * Encapsulate this functionality in AOP aspect for easy reuse
     *
     * If we need to stop the exception or handle the exception we need to use @Around Advice
     *
     * This will run if method throws exception
     */
    @AfterThrowing(pointcut = "execution(* com.spring.hibernate.aop.AccountDao.findAllUsers(..))", throwing = "ex") // Pointcut Expression
    public void loggingAllAfterThrowingAdvice(JoinPoint joinPoint, Throwable ex) {
        log.info("==> Exception " + ex.getMessage());
        log.info("Executing Logging with @AfterThrowing advice on all method(any num of argument))");
    }

    /**
     * AfterAdvice
     * This will run regardless of method outcome success or failure just like finally block
     * This will run before @AfterThrowing if method throws exception
     * MainApp -> APO Proxy -> Method -> @After -> @AfterThrowing
     *
     * It does not have access to exception
     */
    @After("forDaoPackage()") // Pointcut Expression
    public void loggingAllAfterAdvice(JoinPoint joinPoint) {
        // display method signature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.info("Method: " + signature);

        // display method arguments
        Object[] args = joinPoint.getArgs();
        for(Object obj: args){
            log.info(obj.toString());
        }

        log.info("Executing Logging with @After advice on all method(any num of argument))");
    }

    /**
     * AroundAdvice runs before and after the method
     * We can access result
     * We can access exception stop,handle exception
     * Its combination of @Before & @After
     * Used for profiling like how much time taken by method
     *
     * We will get proceeding joint point / handle to execute target method
     */
    @Around("execution(* com.spring.hibernate.aop.AccountDao.fortuneService(..))") // Pointcut Expression
    public Object loggingAllAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long begin = System.currentTimeMillis();

        Object result = null;
        try {
             result = proceedingJoinPoint.proceed();
        } catch (Exception ex) {
            log.error("@Around advice: we have a problem " + ex);
            //result = "Calling program never knows exception occur as we handle the exception, " +
            //        "use with caution otherwise you won't know what is going on in your application";

            // if we want to throw the exception to the caller
             throw ex;
        }

        long end = System.currentTimeMillis();

        log.info("Duration: " + (end - begin) + " milliseconds");

        log.info("Executing Logging with @Around advice on all method(any num of argument))");

        return result;

    }



    /**
     * AfterReturning advice which holds result
     * This will run if method return successfully that mean no exception
     */
    @AfterReturning(pointcut = "execution(* com.spring.hibernate.aop.AccountDao.findAllUsers(..))", returning = "result") // Pointcut Expression
    public void loggingAllAfterReturningAdvice(JoinPoint joinPoint, List<String> result) {

        // return
        for(String str: result){
            log.info(str);
        }

        log.info("Executing Logging with @AfterReturning advice on all method(any num of argument))");
    }
}

