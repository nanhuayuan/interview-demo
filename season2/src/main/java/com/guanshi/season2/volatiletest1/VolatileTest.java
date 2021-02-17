package com.guanshi.season2.volatiletest1;

import java.net.Inet4Address;
import java.util.concurrent.atomic.AtomicInteger;

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

    /**
     *
     * 1验i证olatile的可见性
     * 1.1假如int number=e；，number变量之前很本没有添加加olatile关然字修能，没有可见性
     * 1.2添加了volatile，可以解决可见性问题。
     * 2验证volatile不保证原子性
     * 2.1原子然指的是什么意想？
     * 不可分割，完整性，也即某个线程正在能某个具体业务时，中间不可以被加塞或者被分。需要整体完整要么同时成功，要么同时失妙。
     * 2.2 volatile不保证原子性的案演示
     * 2.3 why
     * 2.4如何解决原子性？I米加sync
     *
     *
     *
     */

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }

    public static void main(String[] args) {
        //seeVolatileTest();
        VolatileTest test = new VolatileTest();
        for (int i = 0; i < 20 ; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    test.addPlus();
                    test.addAtomic();
                }
            }, String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2){
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "获得了最终值"  + test.num );
        System.out.println(Thread.currentThread().getName() + "获得了最终值"  + test.atomicInteger );


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

        while (test.num == 0) {

        }
        System.out.println(Thread.currentThread().getName() + "获得了1");
        /*new Thread(()->{

            System.out.println(Thread.currentThread().getName() + "come in");



        },"线程二").start();*/
    }
}
