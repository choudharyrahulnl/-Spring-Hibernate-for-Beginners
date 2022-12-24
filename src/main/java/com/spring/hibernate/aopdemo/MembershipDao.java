package com.spring.hibernate.aopdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MembershipDao {

    public void addAccount() {
        log.info(getClass() + " Doing my DB work: Adding an account");
    }
}
