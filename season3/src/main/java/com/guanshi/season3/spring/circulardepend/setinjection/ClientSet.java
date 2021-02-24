package com.guanshi.season3.spring.circulardepend.setinjection;

import com.guanshi.season3.spring.circulardepend.constructorinjection.ServiceA;
import com.guanshi.season3.spring.circulardepend.constructorinjection.ServiceB;

/**
 * @author poi 2021/2/22 22:31
 * @version 1.0
 * 2021/2/22 22:31
 */
public class ClientSet {
    public static void main(String[] args) {

        //创建serviceA
        ServiceAA serviceAA = new ServiceAA();

        //创建serviceBB
        ServiceBB serviceBB = new ServiceBB();

        //将serviceAA注入到serviceBB中
        serviceBB.setServiceAA(serviceAA);

        //将serviceBB注入到serviceAA中
        serviceAA.setServiceBB(serviceBB);

    }

}
