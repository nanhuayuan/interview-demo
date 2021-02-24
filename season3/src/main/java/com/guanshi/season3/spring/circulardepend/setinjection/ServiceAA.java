package com.guanshi.season3.spring.circulardepend.setinjection;

import com.guanshi.season3.spring.circulardepend.constructorinjection.ServiceB;
import org.springframework.stereotype.Component;

/**
 * @author poi 2021/2/22 22:28
 * @version 1.0
 * 2021/2/22 22:28
 */
@Component
public class ServiceAA {

    private ServiceBB serviceBB;

    public void setServiceBB(ServiceBB serviceB) {
        this.serviceBB = serviceB;
        System.out.println("A 里面设置了B");
    }
}
