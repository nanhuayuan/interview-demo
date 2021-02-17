package com.guanshi.season2.jvm.oom;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * @author poi 2021/2/14 18:25
 * @version 1.0
 * 2021/2/14 18:25
 * unable to create new native thread
 * 不能创建本地线程了
 *
 * 高并发请求服务器时，经常出现如下景常：java.lang.OutOfNemoryError:unable to create new native thread
 * 性确的讲该native thread是常与对应的平台有关
 * 导致原因：
 * 1你的应用色创建了太多线程了，一个应用进程创建多个线程，超过系统承截截限
 * 2你的服务器并不允许你的应用程序创建这么多能程，Linux系统歉认允许单个进程可以创建的线程数是1024个，
 * 你的应用创建超过这个数量，就会报java.Lang.OutofMemoryError:unable to create new native thread
 *
 * 解决办法：
 * 1.想办法降低你应用程序创建线程的数量，分析应用是否真的需要创建这么多能程，如果不是，读代码将线程数降到最低
 * 2.对于有的应用，确实需要创建很多纪程，运超过linux系统的默认1024个能程的限制剂可以通过修改linux服务器配置扩大Linux默认限制
 *
 */
public class UnableToCreateNewNativeThread {
    public static void main(String[] args) {
        for (int i = 1; ; i++) {
            System.out.println(i);
            new Thread(() -> {
                try { TimeUnit.SECONDS.sleep(Integer.MAX_VALUE); }catch (InterruptedException e) { e.printStackTrace(); }
            }, String.valueOf(i)).start();
        }
        //100649
        //Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread

        //linux 查看命令 ulimit -u

        //修改
        // vim /etc/security/limits.d/90-nproc.conf


    }
}
