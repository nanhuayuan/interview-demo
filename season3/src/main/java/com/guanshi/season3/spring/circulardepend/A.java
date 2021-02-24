package com.guanshi.season3.spring.circulardepend;

/**
 * @author poi 2021/2/22 22:35
 * @version 1.0
 * 2021/2/22 22:35
 */
public class A {
    private B b;

    public B getB(){
        return b;
    }

    public void setB(B b){
        this.b = b;
    }

    public A(){
        System.out.println("---A created success");
    }
}
