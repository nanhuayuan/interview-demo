package com.guanshi.season2.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author poi 2021/2/12 20:29
 * @version 1.0
 * 2021/2/12 20:29
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模仿3个停车位
        for (int i = 1; i <= 6; i++) {//模仿六部汽车
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t  抢到车位");
                    // 休息几秒钟
                    try{
                        TimeUnit.SECONDS.sleep(3);
                    } catch(Exception e){
                        e.printStackTrace();
                    } finally {

                    }

                    System.out.println(Thread.currentThread().getName() + "\t  释放车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }

    }
}
