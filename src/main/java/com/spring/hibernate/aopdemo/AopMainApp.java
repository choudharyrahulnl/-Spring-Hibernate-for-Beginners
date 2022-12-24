package com.spring.hibernate.aopdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class AopMainApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AOPConfig.class);

        AccountDao accountDao = context.getBean("accountDao", AccountDao.class);
        MembershipDao memberShipDao = context.getBean("membershipDao", MembershipDao.class);

        accountDao.addAccount("Rahul Choudhary");
        memberShipDao.addAccount();
        accountDao.findAllUsers(false);

        try {
            accountDao.findAllUsers(true);
        } catch (Exception ex) {
            log.error("Error while calling accountDao.findAllUsers() from AopMainApp");
        }

        log.info(accountDao.fortuneService(false));

        try {
            accountDao.fortuneService(true);
        } catch (Exception ex) {
            log.error("Error while calling accountDao.fortuneService() from AopMainApp");
        }


        context.close();
    }
}
