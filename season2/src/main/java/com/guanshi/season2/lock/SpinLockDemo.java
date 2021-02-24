package com.guanshi.season2.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author poi 2021/2/12 16:31
 * @version 1.0
 * 2021/2/12 16:31
 *
 * 题目：实现一个自旋锁
 * 自旋锁好处：循环比较获取直到成功为止，没有类wait的阻塞。
 * 通过CAs操作完成自旋键，A线程先进来调myLock方法自己持有锁5砂钟，B随后进来后发现
 * 当前有线程持有锁，不是null，所以只能通过自旋等待，直到A释放锁后B随后抢到。|
 */
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void mylock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in");
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void myUnlock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName() + "\t invoked myunlock()");
    }
    public static void main(String[] args) {

        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() ->{
            try {
                spinLockDemo.mylock();
                TimeUnit.SECONDS.sleep(5);
                spinLockDemo.myUnlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AA").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() ->{
            spinLockDemo.mylock();
            spinLockDemo.myUnlock();
        }, "BB").start();

    }


}
