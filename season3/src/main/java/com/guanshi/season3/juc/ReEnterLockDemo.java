package com.guanshi.season3.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author poi 2021/2/17 23:56
 * @version 1.0
 * 2021/2/17 23:56
 *
 * 可重入锁：可重复可递归调用的锁，在外层调用锁之后，在的层内层依然可以使用，并不发生死锁，这样的锁就是可重入锁。
 * 在一个synchronized修饰的方法或代码块的内部
 * 调用本类的其他synchronized修饰的方法或代码块时，是永远可以得到锁
 */
public class ReEnterLockDemo {

    static Object objectLockA = new Object();

    public static void m1(){
        new Thread(() -> {
            synchronized (objectLockA){
                System.out.println(Thread.currentThread().getName() + "\t 外层调用");
                synchronized (objectLockA){
                    System.out.println(Thread.currentThread().getName() + "\t 中层调用");
                    synchronized (objectLockA){
                        System.out.println(Thread.currentThread().getName() + "\t 内层调用");
                    }
                }
            }
        }, "t1").start();
    }


    public synchronized void mf1(){
        System.out.println(Thread.currentThread().getName() + "\t 方法 外层循环");
        mf2();
    }

    public synchronized void mf2(){
        System.out.println(Thread.currentThread().getName() + "\t 方法 中层循环");
        mf3();
    }

    public synchronized void mf3(){
        System.out.println(Thread.currentThread().getName() + "\t 方法 内层循环");
    }

    static Lock lock = new ReentrantLock();

    public static void ml(){
        new Thread(() -> {

            lock.lock();
            try{
              System.out.println(Thread.currentThread().getName() + "\t lock 外层调用");
                lock.lock();
                try{
                    System.out.println(Thread.currentThread().getName() + "\t lock 中层调用");
                    lock.lock();
                    try{
                        System.out.println(Thread.currentThread().getName() + "\t lock 内层调用");
                    } catch(Exception e){
                        e.printStackTrace();
                    }finally{
                        lock.unlock();
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }finally{
                    lock.unlock();
                }
            } catch(Exception e){
                e.printStackTrace();
            }finally{
                lock.unlock();
            }
        }, "t1").start();

    }


    public static void main(String[] args) {

        //m1();
        /*reEnterLockDemoReEnterLockDemo reEnterLockDemo = new ReEnterLockDemo();
        reEnterLockDemo.mf1();*/

        ml();



    }
}
