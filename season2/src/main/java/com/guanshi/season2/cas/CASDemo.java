package com.guanshi.season2.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author poi 2021/2/11 0:06
 * @version 1.0
 * 2021/2/11 0:06
 */
public class CASDemo {

    /**
     * 1.CAS是什么？ ===>compareAndSet
     * 比较并交换
     *
     */

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2019) + "\t current data："  + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024) + "\t current data："  + atomicInteger.get());
    }
}
