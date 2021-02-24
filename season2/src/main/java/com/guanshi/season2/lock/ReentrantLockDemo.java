package com.guanshi.season2.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author poi 2021/2/12 15:44
 * @version 1.0
 * 2021/2/12 15:44
 */
class Phone implements  Runnable{

    public synchronized  void sendSMS(){
        System.out.println(Thread.currentThread().getName() +"\t invoked sendSMS");
        sendEmail();
    }

    public synchronized  void sendEmail(){
        System.out.println(Thread.currentThread().getName() +"\t ########## invoked sendEmail");
    }

    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    /*public void get(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t invoked get()" );
            set();
        } catch (Exception e){

        } finally {
            lock.unlock();
        }
    }*/
    public void get(){
        // lock 和 unlock必须呀数量必须一致
        lock.lock();
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t invoked get()" );
            set();
        } catch (Exception e){

        } finally {
            lock.unlock();
            lock.unlock();
        }
    }

    public void set(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t invoked set()" );
        } catch (Exception e){

        } finally {
            lock.unlock();
        }
    }

}
/**
 * 可重入锁（也叫做递归锁）
 * 指的是同一线程外层的数获得锁之后，内层递归函数用然能获取读锁的代码，
 * 在同一个线程在外层方法获取锁的时候在进入内层方达会自动获取锁也即是说，
 * 线程可以进入任何一个它已经有的锁所同步养的代码块。
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {

        //synchronized
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendSMS();
        },"t1").start();

        new Thread(() -> {
            phone.sendSMS();
        },"t2").start();
        System.out.println("以上为synchronized");

        Thread t3 = new Thread(phone);
        Thread t4 = new Thread(phone);
        t3.start();
        t4.start();

    }
}
