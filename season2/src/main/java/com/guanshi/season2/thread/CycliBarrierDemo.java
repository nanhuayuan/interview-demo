package com.guanshi.season2.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author poi 2021/2/12 20:23
 * @version 1.0
 * 2021/2/12 20:23
 */
public class CycliBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {System.out.println(Thread.currentThread().getName() + "\t 召唤神龙"); });
        for (int i = 1; i <= 7; i++) {
            final int tem = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 收集到第:" + tem + "龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }, String.valueOf(i)).start();
        }
    }
}
