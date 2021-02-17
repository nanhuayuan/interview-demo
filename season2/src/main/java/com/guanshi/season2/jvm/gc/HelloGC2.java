package com.guanshi.season2.jvm.gc;

/**
 * @author poi 2021/2/13 23:18
 * @version 1.0
 * 2021/2/13 23:18
 *
 * Java的Gc回收类型主要有
 * UseSerialGC,UseParallelGC,UseConcMarkSweepGC,UseParNewGC,UseParallelOldGC,UseG1GC
 */
public class HelloGC2 {

    public static void main(String[] args) throws InterruptedException {
        long totalMemory = Runtime.getRuntime().totalMemory();//虚拟机内存信息
        long maxMemory = Runtime.getRuntime().maxMemory();//虚拟机试图使用的最大内存信息
        System.out.println("TOTAL_MEMORY(-Xms)" + totalMemory + "(字节)、" + (totalMemory / (double) 1024 / 1024) + "MB");
        System.out.println("MAX_MEMORY(-Xmx)" + maxMemory + "(字节)、" + (maxMemory / (double) 1024 / 1024) + "MB");

        //java -XX:+PrintCommandLineFlags -version
        //打印
        // -XX:InitialHeapSize=400629184 -XX:MaxHeapSize=6410066944 -XX:+PrintCommandLineFlags -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividua
        //lAllocation -XX:+UseParallelGC
        //java version "1.8.0_51"
        //Java(TM) SE Runtime Environment (build 1.8.0_51-b16)
        //Java HotSpot(TM) 64-Bit Server VM (build 25.51-b03, mixed mode)

        //-XX:+UseSerialGC
        System.out.println("HelloGc");
        Thread.sleep(Integer.MAX_VALUE);
        //查看串行垃圾回收器是否使用
        //jinfo -flag  UseSerialGC


    }
}
