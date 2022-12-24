package com.spring.hibernate.aopdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class AccountDao {

    public void addAccount(String userName) {
        log.info(getClass() + " Doing my DB work: Adding an account for User " + userName);
    }

    public List<String> findAllUsers(boolean b) {
        if(b) throw new RuntimeException("Error while fetching all users for AccountDao");
        return List.of("rahul", "ravi");
    }

    public String fortuneService(boolean b) {

        if(b) throw new RuntimeException("Error while fetching fortuneService for AccountDao");

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        return "Expected heavy traffic";
    }
}
