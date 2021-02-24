package com.guanshi.season3.spring.circulardepend.constructorinjection;

/**
 * @author poi 2021/2/22 22:25
 * @version 1.0
 * 2021/2/22 22:25
 */
/**
 * 通过构造器的方式注入依赖，构造器的方式注入依赖的bean，下面两个bean循环依赖
 *
 * 测试后发现，构造器循环依赖是无法解决的
 */
public class ClientConstructor {
    public static void main(String[] args) {
        //new ServiceA(new ServiceB(new ServiceA(new ServiceB()))); ....
    }
}

