package com.guanshi.season2.thread;


import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB){
          this.lockA = lockA;
          this.lockB = lockB;
    }
    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + "\t 自己持有:" + lockA + "\t尝试获得:" + lockB);
            try { TimeUnit.SECONDS.sleep(2); }catch (InterruptedException e) { e.printStackTrace(); }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "\t 自己持有:" + lockB + "\t尝试获得:" + lockA);
            }
        }

    }
}
/**
 * @author poi 2021/2/13 18:28
 * @version 1.0
 * 2021/2/13 18:28
 * 死锁是指两个或两个以上的进程在执行过程中，
 * 因争夺资源而造成的一种互相等待的现象，
 * 若无外力干涉那它们都将无法能进下去
 */
public class DeadLock {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA,lockB), "ThreadAAA").start();
        new Thread(new HoldLockThread(lockB,lockA), "ThreadBBB").start();
    }
}
