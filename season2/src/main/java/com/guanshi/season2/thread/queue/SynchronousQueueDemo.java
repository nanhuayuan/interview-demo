package com.guanshi.season2.thread.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author poi 2021/2/12 23:15
 * @version 1.0
 * 2021/2/12 23:15
 * SynchronousQueue（重要）：不存储元素的阻塞队列，也即单个元素的队列。
 * 每一个put操作必须要等待一个take操作，否则不能继续添加元素，反之亦然。
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t put 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName() + "\t put 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName() + "\t put 3");
                blockingQueue.put("3");

            } catch (Exception e){

            }

        }, "AAA").start();

        new Thread(() -> {
            try {
                try { TimeUnit.SECONDS.sleep(5); }catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName() + "\t 获得" + blockingQueue.take());
                try { TimeUnit.SECONDS.sleep(5); }catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName() + "\t 获得" + blockingQueue.take());
                try { TimeUnit.SECONDS.sleep(5); }catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName() + "\t 获得" + blockingQueue.take());

            } catch (Exception e){

            }

        }, "BBB").start();
    }
}
