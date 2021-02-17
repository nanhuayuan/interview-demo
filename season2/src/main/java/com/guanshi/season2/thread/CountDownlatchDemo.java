package com.guanshi.season2.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author poi 2021/2/12 19:44
 * @version 1.0
 * 2021/2/12 19:44
 */
public class CountDownlatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t  国被灭");
                countDownLatch.countDown();
            }, CountyEnum.forEach_CountyEnum(i).getRetMessage()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t 秦灭六国，一统华夏");

    }
}
