package com.guanshi.season2.jvm.ref;

/**
 * @author poi 2021/2/14 11:24
 * @version 1.0
 * 2021/2/14 11:24
 */
public class StrongReferenceDemo {

    public static void main(String[] args) {
        Object o1 = new Object();//强引用
        Object o2 = o1;//赋值引用
        o1 = null;//置空
        System.gc();
        System.out.println(o2);


    }
}
