package com.guanshi.season3.spring.circulardepend;

/**
 * @author poi 2021/2/22 22:35
 * @version 1.0
 * 2021/2/22 22:35
 */
public class B {
    private A a;

    public A getA(){
        return a;
    }

    public void setA(A a){
        this.a = a;
    }


    public B(){
        System.out.println("---B created success");

    }
}
