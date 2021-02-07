package com.guanshi.season2.volatiletest1;

import java.net.Inet4Address;

/**
 * @Description: VolatileTest
 * @Author: songkai
 * @Date: 2021/2/7
 * @Version: 1.0
 */
public class VolatileTest {

    volatile int num = 0;

    public void addPlus(){
        num++;
    }

    public static void main(String[] args) {
        VolatileTest test = new VolatileTest();
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName() + "come in");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + "sleep off");
                test.addPlus();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"线程一").start();


    }

    public static void seeVolatileTest() {
        VolatileTest test = new VolatileTest();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName() + "come in");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + "sleep off");
                test.addPlus();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"线程一").start();

        new Thread(()->{

            System.out.println(Thread.currentThread().getName() + "come in");
            while (test.num == 0) {

            }
            System.out.println(Thread.currentThread().getName() + "获得了1");

        },"线程二").start();
    }
}
