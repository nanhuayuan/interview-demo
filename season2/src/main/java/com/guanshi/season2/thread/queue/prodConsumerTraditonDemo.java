package com.guanshi.season2.thread.queue;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class  ShareData{

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws  Exception{
        lock.lock();
        try{
            //1判断
            while (number != 0){
                //等待，不能生产
                condition.await();
            }
            //2.干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t " + number);
            //3.通知唤醒
            condition.signalAll();
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void decrment() throws  Exception{
        lock.lock();
        try{
            //1判断
            while (number == 0){
                //等待，不能消费
                condition.await();
            }
            //2.干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t " + number);
            //3.通知唤醒
            condition.signalAll();
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}



/**
 * @author poi 2021/2/12 23:28
 * @version 1.0
 * 2021/2/12 23:28
 *
 * 题目：一个初始值为零的变量，两个线程利其交操作，一个加一个边，来5轮
 * 1 线程     操作（方法） 资源类
 * 2 判断     干活          通知唤醒
 * 3 防止虚假唤醒
 */
public class prodConsumerTraditonDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();

            new Thread(() -> {
                for (int i = 1; i <= 5; i++) {
                    try {
                        shareData.increment();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, "AAA").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.decrment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "BBB").start();


    }

}
