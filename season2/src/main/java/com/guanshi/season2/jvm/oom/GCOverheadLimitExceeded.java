package com.guanshi.season2.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author poi 2021/2/14 18:25
 * @version 1.0
 * 2021/2/14 18:25
 * GCDemo overhead limit exceeded
 * 程序在垃圾回收上花费了98%的时间，却收集不回2%的空间，通常这样的异常伴随着CPU的冲高
 *
 * Gc回收时间过长时会抛出0utofMemroyError。过长的定义是，超过98%的时间用来做GC并互回收了不到2%的维内存
 * 连续多次GC， 都只回收了不到2%的极端信况下才会抛出。假如不抛出GC overhead limit 错误会发生什么情况呢？
 * 那就是GC清理的这么点内存很快会再次填满，迫使GC再次执行.这样就形成恶性循环，
 * CPU使用率一直是100%，而GC却没有任何成果
 *
 */
public class GCOverheadLimitExceeded {
    public static void main(String[] args) {
        //-Xms10m -Xmx10m -XX:MaxDirectMemorySize=5m
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while(true){
                list.add(String.valueOf(++i).intern());
            }
        }catch(Throwable e) {
            System.out.println("**************i:" + i);
            e.printStackTrace();
            throw e;
        }
        //java.lang.OutOfMemoryError: GCDemo overhead limit exceeded
    }
}
