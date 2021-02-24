package com.guanshi.season2.jvm.gc;

import sun.misc.GC;

import java.util.Random;

/**
 * @author poi 2021/2/16 15:59
 * @version 1.0
 * 2021/2/16 15:59
 *  * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC  后面单独讲G1
 *  *
 *  * 从官网的描述中，我们知道G1是一种服务器端的垃圾收集器，
 *  * 应用在多处理器和大容量内存环境中，在实现高吞吐量的同时，尽可能的满足垃圾收集暂停时间的要求。另外，它还具有以下特性：
 *  *      像CMS收集器一样，能与应用程序线程并发执行。
 *  *      整理空闲空间更快。
 *  *      需要更多的时间来预测GC停顿时间。
 *  *      不希望牺牲大量的吞吐性能。
 *  *      不需要更大的Java Heap。
 *  *
 *  * G1收集器的设计目标是取代CMS收集器，它同CMS相比，在以下方面表现的更出色：
 *  * G1是一个有整理内存过程的垃圾收集器，不会产生很多内存碎片。
 *  * G1的Stop The World（STW）更可控，G1在停顿时间上添加了预测机制，用户可以指定期望停顿时间。
 *  *
 *  * CMS垃圾收集器虽然减少了暂停应用程序的运行时间，但是它还是存在着内存碎片问题。
 *  * 于是，为了去除内存碎片问题，同时又保留CMS垃圾收集器低暂停时间的优点，JAVA7发布了一个新的垃圾收集器-G1垃圾收集器。
 *  *
 *  * G1是在2012年才在jdk1.7u4中可用。oracle官方计划jdk9中将G1变成默认的垃圾收集器以替代CMS。
 *  * 它是一款面向服务端应用的收集器，主要应用在多CPU和大内存服务器环境下，极大的减少垃圾收集的停顿时间，
 *  * 全面提升服务器的性能，逐步替换java8以前的CMS收集器。
 *  *
 *  * 主要改变是Eden，Survivor和Tenured等内存区域不再是连续的了，
 *  * 而是变成了一个个大小一样的region，每个region从1M到32M不等。
 *  * 一个region有可能属于Eden，Survivor或者Tenured内存区域。
 *  * 特点:
 *  *      1：G1能充分利用多CPU、多核环境硬件优势，尽量缩短STW。
 *  *      2：G1整体上采用标记-整理算法，局部是通过复制算法，不会产生内存碎片。
 *  *      3：宏观上看G1之中不再区分年轻代和老年代。把内存划分成多个独立的子区域（Region），可以近似理解为一个围棋的棋盘。
 *  *      4：G1收集器里面讲整个的内存区都混合在一起了，但其本身依然在小范围内要进行年轻代和老年代的区分，保留了新生代和老年代，但它们不再是物理隔离的，而是一部分Region的集合且不需要Region是连续的，也就是说依然会采用不同的GC方式来处理不同的区域。
 *  *      5：G1虽然也是分代收集器，但整个内存分区不存在物理上的年轻代与老年代的区别，也不需要完全独立的survivor（to space）堆做复制准备。G1只有逻辑上的分代概念，或者说每个分区都可能随G1的运行在不同代之间前后切换；
 *  * 步骤:
 *  *      初始标记：只标记GC Roots能直接关联到的对象
 *  *      并发标记：进行GC Roots Tracing的过程
 *  *      最终标记：修正并发标记期间，因程序运行导致标记发生变化的那一部分对象
 *  *      筛选回收：根据时间来进行价值最大化的回收形如：
 *
 *  常用参数:
 *  -XX:+UseG1GC  开启G1
 *  -XX:G1HeapRegionSize=n  设置的G1区域的大小。值是2的幂，范围是1MB到32MB。目标是根据最小的Java堆大小划分出约2048个区域。
 *  -XX:MaxGCPauseMilis=n：最大Gc停顿时间，这是个软目标，JVM将尽可能（但不保证）停顿小于这个时间
 *  -XX:InitiatingHeapOccupancyPercent=n：堆占用了多少的时候就触发Gc，默认为45
 *  -XX:ConcGCThreads=n：并发Gc使用的线程数
 *  -XX:G1ReservePercent=n：设置作为空闲空间的预留内存百分比，以降低目标空间溢出的风险，默认值是10%
 *
 *  比起cms有两个优势：
 * 1）G1不会产生内存碎片。
 * 2）是可以精确控制停顿。该收集器是把整个堆（新生代、老生代）划分成多个固定大小的区域，每次根据允许停顿的时间去收集垃圾最多的区域。
 */
public class G1Demo {
    public static void main(String[] args) {
        String str = "guanshi";
        try {
            while (true){
                str = str + new Random().nextInt(1111111)+ + new Random ().nextInt(22222222);
                str.intern();
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {

        }
        //-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC

        //-XX:InitialHeapSize=10485760 -XX:MaxHeapSize=10485760 -XX:+PrintCommandLineFlags
        // -XX:+PrintGCDetails -XX:+UseCompressedClassPointers -XX:+UseCompressedOops
        // -XX:+UseG1GC -XX:-UseLargePagesIndividualAllocation

        //初始标记
        //[GC pause (G1 Evacuation Pause) (young), 0.0021234 secs]

        //并发标记
        //[GC concurrent-root-region-scan-start]
        //[GC concurrent-root-region-scan-end, 0.0001960 secs]
        //[GC concurrent-mark-start]
        //[GC concurrent-mark-end, 0.0009123 secs]
        //
        //最终标记
        // [GC remark [Finalize Marking, 0.0003171 secs] [GC ref-proc, 0.0003532 secs] [Unloading, 0.0005245 secs], 0.0014681 secs]
        // [Times: user=0.00 sys=0.00, real=0.00 secs]
        //[GC cleanup 7575K->6551K(10M), 0.0006393 secs]
        // [Times: user=0.00 sys=0.00, real=0.00 secs]

        // 筛选回收
        // [GC concurrent-cleanup-start]
        //[GC concurrent-cleanup-end, 0.0000084 secs]
        //[GC pause (G1 Evacuation Pause) (young), 0.0013322 secs]
    }

}
