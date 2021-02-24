package com.guanshi.season3.spring.circulardepend;

/**
 * @author poi 2021/2/22 22:36
 * @version 1.0
 * 2021/2/22 22:36
 */
public class ClientCode {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();

        a.setB(b);
        b.setA(a);
    }

}
