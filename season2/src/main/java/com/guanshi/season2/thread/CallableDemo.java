package com.guanshi.season2.thread;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread1 implements Runnable{

    @Override
    public void run() {

    }
}

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t callable come in");
        try { TimeUnit.SECONDS.sleep(2); }catch (InterruptedException e) { e.printStackTrace(); }
        return 1024;
    }
}

/**
 * @author poi 2021/2/13 11:15
 * @version 1.0
 * 2021/2/13 11:15
 */
public class CallableDemo  {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        FutureTask<Integer> futureTask2 = new FutureTask<>(new MyThread());

        new Thread(futureTask,"AA").start();
        new Thread(futureTask,"BB").start();//不会执行，一个futrueTask，多个线程，只会执行一次
        new Thread(futureTask2,"CC").start();//会执行，

        int re1 = 100;

        //计算完成futureTask.isDone()
        /*while (!futureTask.isDone()){

        }*/
        Integer re2 = futureTask.get();//获得callable线程的计算结果，如果没有完成，会导致阻塞

        System.out.println("结果值:" + (re1 + re2));

    }
}
