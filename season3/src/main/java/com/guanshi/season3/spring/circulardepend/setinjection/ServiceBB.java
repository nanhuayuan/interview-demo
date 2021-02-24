package com.guanshi.season3.spring.circulardepend.setinjection;

import com.guanshi.season3.spring.circulardepend.constructorinjection.ServiceB;
import org.springframework.stereotype.Component;

/**
 * @author poi 2021/2/22 22:28
 * @version 1.0
 * 2021/2/22 22:28
 */
@Component
public class ServiceBB {

    private ServiceAA serviceAA;

    public void setServiceAA(ServiceAA serviceAA) {
        this.serviceAA = serviceAA;
        System.out.println("B 里面设置了A");
    }
}
