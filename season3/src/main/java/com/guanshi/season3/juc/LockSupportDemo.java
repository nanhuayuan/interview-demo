package com.guanshi.season3.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author poi 2021/2/19 21:49
 * @version 1.0
 * 2021/2/19 21:49
 */
public class LockSupportDemo {
    static Object objectLockA = new Object();

    public static void m1(){
        new Thread(() -> {
            synchronized (objectLockA){
                System.out.println(Thread.currentThread().getName() + "\t come in");
                synchronized (objectLockA){
                    System.out.println(Thread.currentThread().getName() + "\t 中层调用");
                    synchronized (objectLockA){
                        System.out.println(Thread.currentThread().getName() + "\t 内层调用");
                    }
                }
            }
        }, "t1").start();
    }

    public static void main(String[] args) {
        lockSupportParkAndUnpark2();

    }

    /**
     * 先休眠，在唤醒
     */
    public static void lockSupportParkAndUnpark(){
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            LockSupport.park();//被阻塞.….等待道知等待放行，它要道过需要许可加
            System.out.println(Thread.currentThread().getName() + "\t -----被唤醒");
        }, "A");
        a.start();

        try { TimeUnit.SECONDS.sleep(3); }catch (InterruptedException e) { e.printStackTrace(); }
        Thread b = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 通知了");
            LockSupport.unpark(a);//被阻塞.….等待道知等待放行，它要道过需要许可加
        }, "B");
        b.start();
    }

    /**
     * 先唤醒，再休眠
     */
    public static void lockSupportParkAndUnpark2(){
        Thread a = new Thread(() -> {
            try { TimeUnit.SECONDS.sleep(3); }catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "\t come in");
            LockSupport.park();//被阻塞.….等待道知等待放行，它要道过需要许可加
            System.out.println(Thread.currentThread().getName() + "\t -----被唤醒");
        }, "A");
        a.start();

        Thread b = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 通知了");
            LockSupport.unpark(a);//被阻塞.….等待道知等待放行，它要道过需要许可加
        }, "B");
        b.start();
    }

    public static void synchronizedWaitNotify(){
        new Thread(() -> {
            synchronized (objectLockA){
                System.out.println(Thread.currentThread().getName() + "\t come in");
                try {
                    objectLockA.wait();
                } catch (Exception e){
                    e.printStackTrace();
                } finally {

                }
                System.out.println(Thread.currentThread().getName() + "\t -----被唤醒");

            }
        }, "A").start();

        new Thread(() -> {
            synchronized (objectLockA){
                objectLockA.notify();
                System.out.println(Thread.currentThread().getName() + "\t --通知");
                try {
                    objectLockA.wait();
                } catch (Exception e){
                    e.printStackTrace();
                } finally {

                }
            }
        }, "B").start();
    }
}