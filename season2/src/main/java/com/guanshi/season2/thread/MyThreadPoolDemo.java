package com.guanshi.season2.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author poi 2021/2/13 12:33
 * @version 1.0
 * 2021/2/13 12:33
 */
public class MyThreadPoolDemo {

    /*
    创建线程池底部使用就是 ThreadPoolExecutor

     */

    public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }

    /*
    corePoolSize        常驻核心线程数
    maximumPoolSize     线程池线程最大数量-线程池能够容纳同时执行的最大线程数,此值大于等于1
    keepAliveTime       活跃时间-多余的空闲线程存活时间,当空间时间达到keepAliveTime值时,多余的线程会被销毁直到只剩下corePoolSize个线程为止
    unit                时间单位-keepAliveTime的单位
    workQueue           工作队列-任务队列,被提交但尚未被执行的任务.
    threadFactory       线程工厂-表示生成线程池中工作线程的线程工厂,用户创建新线程,一般用默认即可
    handler             拒绝策略,表示当线程队列满了并且工作线程大于等于线程池的最大显示 数(maxnumPoolSize)时如何来拒绝.

    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
        if (corePoolSize < 0 ||
            maximumPoolSize <= 0 ||
            maximumPoolSize < corePoolSize ||
            keepAliveTime < 0)
            throw new IllegalArgumentException();
        if (workQueue == null || threadFactory == null || handler == null)
            throw new NullPointerException();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
    }*/

    /*
        1.在创建了线程池后，等待提交过来的任务请求。
        2.当调用execute（）方法添加一个请求任务时，线程池会做如下判断：
            2.1如果正在运行的线程数量小于corePoolSize，那么马上创建线程运行这个任务；
            2.2如果正在运行的线程数量大于或等于corePoolSize，那么将这个任务放入队列；
            2.3如果这时候队列满了且正在运行的线程数量还小于maximumPoolSize，那么还是要创建非核心线程立刻运行这个任务；
            2.4如果队列满了且正在运行的线程数量大于或等于maximumPoolSize，那么线程池会启动饱和拒绝策略来执行。
        3.当一个线程完成任务时，它会从队列中取下一个任务来执行。
        4.当一个线程无事可做超过一定的时间（keepAliveTime）时，线程池会判断：
        如果当前运行的线程数大于corePoolSize，那么这个线程就被停掉。所以线程池的所有任务完成后它最终会收缩到corePoolSize的大小。
     */

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() ->{
                System.out.println(Thread.currentThread().getName() + "\t 办理业务");

            });
        }

    }

    public static void threadPoolinit() {
        //一池五线程
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //一池一线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //一池N线程
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            //模拟十个用户办理业务
            //两种方法 1.execute；2.submit

            for (int i = 0; i < 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                    //try { TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e) { e.printStackTrace(); }
                    try { TimeUnit.MICROSECONDS.sleep(200); }catch (InterruptedException e) { e.printStackTrace(); }
                });
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
