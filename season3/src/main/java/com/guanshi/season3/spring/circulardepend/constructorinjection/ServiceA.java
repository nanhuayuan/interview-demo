package com.guanshi.season3.spring.circulardepend.constructorinjection;

import org.springframework.stereotype.Component;

/**
 * @author poi 2021/2/22 22:24
 * @version 1.0
 * 2021/2/22 22:24
 */
@Component
public class ServiceA {

    private ServiceB serviceB;

    public ServiceA(ServiceB serviceB) {
        this.serviceB = serviceB;
    }
}

