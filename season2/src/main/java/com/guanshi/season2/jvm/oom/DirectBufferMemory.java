package com.guanshi.season2.jvm.oom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author poi 2021/2/14 18:25
 * @version 1.0
 * 2021/2/14 18:25
 * Direct buffer memory
 * 导致原因：
 * 写NIo程字经常使用ByteBuffer来读取或者写入数据，这是一种基于通道（Channel）与缓冲区（Buffer）的I/0方式，
 * 它可以使用Native函数库直接分配堆外内存，然后通过一个存储在 Java蜂星面的DirectByteBuffer对象作为这统内存的引用进行操作。
 * 这样能在一些场景中显著提高性能，因为避免了在Java堆和Native 堆中来回复制数据。
 * ByteBuffer.allocate（capability）第一种方式是分JVW堆内存，属于GC管溶范圈，由于需要拷贝所以速度相对较慢
 * ByteBuffer.allocteDirect（capability）第一种方式是分OS本地内存，不属GC管糖范围，由于不需要内存拷贝所以速度相对较快。
 * 但如果不断分配本地内存，堆内存很少使用，那么JVM就不需要执行GC，DirectByteBuffer对象们就不会被回收，
 * 这时候堆内存充足，但本地内存可能已经使用光了，再次尝试分配本地内存就会出现0utOfMemoryError，那程就直接崩赞了。
 *
 */
public class DirectBufferMemory {
    public static void main(String[] args) {
        //-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
        System.out.println("配置的MaxDirectMemorySize:" + (sun.misc.VM.maxDirectMemory() / (double)1024 / 1024) + "MB");
        try { TimeUnit.SECONDS.sleep(3); }catch (InterruptedException e) { e.printStackTrace(); }
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);
        //Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory

    }
}
