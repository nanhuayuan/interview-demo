package com.guanshi.season2.jvm.gc;

import java.util.Random;

/**
 * @author poi 2021/2/15 18:00
 * @version 1.0
 * 2021/2/15 18:00
 *  1.
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC (DefNew + Tenured)
 *  开启后会使用：Serial（Young区用）+Serial Old（Old区用）的收集器组合
 *  表示：新生代、老年代都会使用串行回收收集器，新生代使用复制算法，老年代使用标记-整理算法
 *  2.
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC (ParNew + Tenured)
 *   常用对应JVM参数：-XX：+UseParNewGC
 *   启用ParNew收集器，只影响新生代的收集，不影响老年代
 *   开启上述参数后，会使用：ParNew（Young区用）+Serial Old的收集器组合，新生代使用复制算法，老年代采用标记-整理算法
 *
 *   但是，ParNew+Tenured这样的搭配，java8已经不再被推荐
 *   Java HotSpot（TM）64-Bit Server VM warning：
 *   Using the ParNewlyoung collector with the Serial old collector is deprecated and will likely be removed in a future release
 *  备注
 * -XX:ParallelGCThreads限制线程数量，默认开启和CPU数目相同的线程数
 * -Xms10m -Xmx10m -XX：+PrintGCDetails-XX：+UseParNewGC
 *
 *  3.
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC (PSYoungGen + ParOldGen)
 *
 * Parallel Scavenge收集器类似ParNew也是一个新生代垃圾收集器，使用复制算法，
 * 也是一个并行的多线程的垃圾收集器，俗称吞吐量优先收集器。一句话：串行收集器在新生代和老年代的并行化
 *
 * 它重点关注的是：
 * 可控制的吞吐量（Thoughput=运行用户代码时间/（运行用户代码时间+垃圾收集时间），
 * 也即比如程序运行100分钟，垃圾收集时间1分钟，吞吐量就是99%）。
 * 高吞吐量意味着高效利用CPU的时间，它多用于在后台运算而不需要太多交互的任务。
 *
 * 自适应调节策略也是ParallelScavenge 收集器与ParNew收集器的一个重要区别。
 * （自适应调节策略：虚拟机会根据当前系统的运行情况收集性能监控信息，动态调整这些参数以提供最合适的停顿时间（-XX:MaxGCPauseMillis）或最大的吞吐量。
 *
 * 常用JVM参数：-XX：+UseParallelGC或-XX：+UseParallelOldGC（可互相激活）使用Parallel Scanvenge收集器
 * 开启该参数后：新生代使用复制算法，老年代使用标记-整理算法
 *
 * 多说一句：-XX:ParallelGCThreads=数字N表示启动多少个GC线程
 * cpu>8 N=5/8
 * cpu<8 N=实际个数
 *
 *  4
 *  Parallel Old收集器是Parallel Scavenge的老年代版本，使用多线程的标记-整理算法，Parallel Old收集器在JDK1.6才开始提供。
 *
 * 在JDK1.6之前，新生代使用ParallelScavenge 收集器只能搭配年老代的Serial Old收集器，
 * 只能保证新生代的吞吐量优先，无法保证整体的吞吐量。在JDK1.6之前（Parallel Scavenge+Serial Old）
 *
 * Parallel Old正是为了在年老代同样提供吞吐量优先的垃圾收集器，如果系统对吞吐量要求比较高，
 * JDK1.8后可以优先考虑新生代Parallel Scavenge和年老代Parallel Old 收集器的搭配策略。
 * 在JDK1.8及后（Parallel Scavenge+Parallel Old）
 *
 * JVM常用参数：
 * -XX：+UseParallelOldGC 使用Parallel Old收集器，设置该参数后，新生代Parallel+老年代Parallel Old
 *
 *  4.1
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC (PSYoungGen + ParOldGen)
 *  4.2 不加UseParallelOldGC
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags                        (PSYoungGen + ParOldGen)
 *
 *  5
 *  CMS收集器（Concurrent Mark Sweep：并发标记清除）是一种以获取最短回收停顿时间为目标的收集器。
 * 适合应用在互联网站或者B/S系统的服务器上，这类应用尤其重视服务器的响应速度，希望系统停顿时间最短。
 * CMS非常适合堆内存大、CPU核数多的服务器端应用，也是G1出现之前大型应用的首选收集器。
 *
 * 并发标记清除收集器
 * 并发标记清除收集器组合ParNew+CMS+Serial Old
 * Concurrent Mark Sweep并发标记清除，并发收集低停顿，并发指的是与用户线程一起执行
 *
 * 开启该收集器的JVM参数：-XX：+UseConcMarkSweepGC
 * 开启该参数后会自动将-XX：+UseParNewGC打开
 *
 * 开启该参数后，使用ParNew（Young区用）+CMS（Old区用）+Serial Old的收集器组合，Serial Old将作为CMS出错的后备收集器
 *
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC
 *
 * 6.
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC  后面单独讲G1
 *
 * 从官网的描述中，我们知道G1是一种服务器端的垃圾收集器，
 * 应用在多处理器和大容量内存环境中，在实现高吞吐量的同时，尽可能的满足垃圾收集暂停时间的要求。另外，它还具有以下特性：
 *      像CMS收集器一样，能与应用程序线程并发执行。
 *      整理空闲空间更快。
 *      需要更多的时间来预测GC停顿时间。
 *      不希望牺牲大量的吞吐性能。
 *      不需要更大的Java Heap。
 *
 * G1收集器的设计目标是取代CMS收集器，它同CMS相比，在以下方面表现的更出色：
 * G1是一个有整理内存过程的垃圾收集器，不会产生很多内存碎片。
 * G1的Stop The World（STW）更可控，G1在停顿时间上添加了预测机制，用户可以指定期望停顿时间。
 *
 * CMS垃圾收集器虽然减少了暂停应用程序的运行时间，但是它还是存在着内存碎片问题。
 * 于是，为了去除内存碎片问题，同时又保留CMS垃圾收集器低暂停时间的优点，JAVA7发布了一个新的垃圾收集器-G1垃圾收集器。
 *
 * G1是在2012年才在jdk1.7u4中可用。oracle官方计划jdk9中将G1变成默认的垃圾收集器以替代CMS。
 * 它是一款面向服务端应用的收集器，主要应用在多CPU和大内存服务器环境下，极大的减少垃圾收集的停顿时间，
 * 全面提升服务器的性能，逐步替换java8以前的CMS收集器。
 *
 * 主要改变是Eden，Survivor和Tenured等内存区域不再是连续的了，
 * 而是变成了一个个大小一样的region，每个region从1M到32M不等。
 * 一个region有可能属于Eden，Survivor或者Tenured内存区域。
 * 特点:
 *      1：G1能充分利用多CPU、多核环境硬件优势，尽量缩短STW。
 *      2：G1整体上采用标记-整理算法，局部是通过复制算法，不会产生内存碎片。
 *      3：宏观上看G1之中不再区分年轻代和老年代。把内存划分成多个独立的子区域（Region），可以近似理解为一个围棋的棋盘。
 *      4：G1收集器里面讲整个的内存区都混合在一起了，但其本身依然在小范围内要进行年轻代和老年代的区分，保留了新生代和老年代，但它们不再是物理隔离的，而是一部分Region的集合且不需要Region是连续的，也就是说依然会采用不同的GC方式来处理不同的区域。
 *      5：G1虽然也是分代收集器，但整个内存分区不存在物理上的年轻代与老年代的区别，也不需要完全独立的survivor（to space）堆做复制准备。G1只有逻辑上的分代概念，或者说每个分区都可能随G1的运行在不同代之间前后切换；
 * 步骤:
 *      初始标记：只标记GC Roots能直接关联到的对象
 *      并发标记：进行GC Roots Tracing的过程
 *      最终标记：修正并发标记期间，因程序运行导致标记发生变化的那一部分对象
 *      筛选回收：根据时间来进行价值最大化的回收形如：
 *
 * 7（理论知道即可，头际中java8已经被优化掉了，没有了。）
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialOldGC
 *
 *  * 下面是故意繁琐配置，主要是为了学习，一般生产不这么配置：
 *  * 下面是故意繁琐配置，主要是为了学习，一般生产不这么配置：
 *  * 下面是故意繁琐配置，主要是为了学习，一般生产不这么配置：
 *  * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags  -XX:+UseParallelGC -XX:+UseParallelOldGC （PsYoungGen+Pa
 *  * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags  -XX:+UseParNewGC -XX:+UseConcMarkSweepGC（par new gener
 *  *
 */
public class GCDemo {
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

        //1
        // -XX:InitialHeapSize=10485760 -XX:MaxHeapSize=10485760 -XX:+PrintCommandLineFlags
        // -XX:+PrintGCDetails -XX:+UseCompressedClassPointers -XX:+UseCompressedOops
        // -XX:-UseLargePagesIndividualAllocation -XX:+UseSerialGC
        // [GC (Allocation Failure) [DefNew: 2751K->320K(3072K), 0.0014106 secs] 2751K->997K(9920K), 0.0014386 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        //[GC (Allocation Failure) [DefNew: 3035K->3035K(3072K), 0.0000085 secs][Tenured: 6767K->1013K(6848K), 0.0018277 secs] 9802K->1013K(9920K), [Metaspace: 3345K->3345K(1056768K)], 0.0018607 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]

        //2
        // -XX:InitialHeapSize=10485760 -XX:MaxHeapSize=10485760 -XX:+PrintCommandLineFlags
        // -XX:+PrintGCDetails -XX:+UseCompressedClassPointers -XX:+UseCompressedOops
        // -XX:-UseLargePagesIndividualAllocation -XX:+UseParNewGC

        //[GC (Allocation Failure) [ParNew: 2752K->319K(3072K), 0.0011342 secs] 2752K->1034K(9920K), 0.0011702 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        //[GC (Allocation Failure) [ParNew: 3050K->3050K(3072K), 0.0000083 secs][Tenured: 6569K->1258K(6848K), 0.0015912 secs] 9619K->1258K(9920K), [Metaspace: 3344K->3344K(1056768K)], 0.0016228 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]

        //3
        //-XX:InitialHeapSize=10485760 -XX:MaxHeapSize=10485760 -XX:+PrintCommandLineFlags
        // -XX:+PrintGCDetails -XX:+UseCompressedClassPointers -XX:+UseCompressedOops
        // -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC

        //[GC (Allocation Failure) [PSYoungGen: 2048K->504K(2560K)] 2048K->929K(9728K), 0.0009479 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        //[Full GC (Ergonomics) [PSYoungGen: 437K->0K(2560K)] [ParOldGen: 6489K->1621K(7168K)] 6927K->1621K(9728K), [Metaspace: 3345K->3345K(1056768K)], 0.0038386 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]

        //4
        //-XX:InitialHeapSize=10485760 -XX:MaxHeapSize=10485760 -XX:+PrintCommandLineFlags
        // -XX:+PrintGCDetails -XX:+UseCompressedClassPointers -XX:+UseCompressedOops
        // -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelOldGC

        //[GC (Allocation Failure) [PSYoungGen: 2048K->496K(2560K)] 2048K->957K(9728K), 0.0009024 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        //[Full GC (Ergonomics) [PSYoungGen: 429K->0K(2560K)] [ParOldGen: 6935K->1425K(7168K)] 7365K->1425K(9728K), [Metaspace: 3345K->3345K(1056768K)], 0.0032421 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]


        //5

        //6
        // -XX:InitialHeapSize=10485760 -XX:MaxHeapSize=10485760 -XX:+PrintCommandLineFlags
        // -XX:+PrintGCDetails -XX:+UseCompressedClassPointers -XX:+UseCompressedOops
        // -XX:+UseG1GC -XX:-UseLargePagesIndividualAllocation

    }
}
