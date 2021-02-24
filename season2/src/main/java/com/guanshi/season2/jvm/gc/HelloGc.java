package com.guanshi.season2.jvm.gc;

/**
 * @author poi 2021/2/13 23:18
 * @version 1.0
 * 2021/2/13 23:18
 */
public class HelloGc {

    public static void main(String[] args) throws InterruptedException {
        long totalMemory = Runtime.getRuntime().totalMemory();//虚拟机内存信息
        long maxMemory = Runtime.getRuntime().maxMemory();//虚拟机试图使用的最大内存信息
        System.out.println("TOTAL_MEMORY(-Xms)" + totalMemory + "(字节)、" + (totalMemory / (double) 1024 / 1024) + "MB");
        System.out.println("MAX_MEMORY(-Xmx)" + maxMemory + "(字节)、" + (maxMemory / (double) 1024 / 1024) + "MB");

        //-XX:+PrintGCDetails -XX:MetaspaceSize=128m -XX:MaxTenuringThreshold=15
        System.out.println("HelloGc");
        //Thread.sleep(Integer.MAX_VALUE);

        //-XX:+PrintGCDetails
        //输出
        // Heap
        // PSYoungGen      total 114688K, used 7864K [0x0000000740a00000, 0x0000000748a00000, 0x00000007c0000000)
        //  eden space 98304K, 8% used [0x0000000740a00000,0x00000007411ae390,0x0000000746a00000)
        //  from space 16384K, 0% used [0x0000000747a00000,0x0000000747a00000,0x0000000748a00000)
        //  to   space 16384K, 0% used [0x0000000746a00000,0x0000000746a00000,0x0000000747a00000)
        // ParOldGen       total 262144K, used 0K [0x0000000641e00000, 0x0000000651e00000, 0x0000000740a00000)
        //  object space 262144K, 0% used [0x0000000641e00000,0x0000000641e00000,0x0000000651e00000)
        // Metaspace       used 3288K, capacity 4496K, committed 4864K, reserved 1056768K
        //  class space    used 351K, capacity 388K, committed 512K, reserved 1048576K

        //-Xms10m -Xmx10m -XX:+PrintGCDetails



        //byte[] bytes = new byte[50 * 1024 * 1024];//50M
        //报错 Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        //                          类型      之前占用 之后 总大小  前堆内存 后堆内存 堆总大小   耗时                用户耗时   系统耗时     实际耗时
        //[GCDemo (Allocation Failure) [PSYoungGen: 604K->496K(2560K)] 1090K->1173K(9728K), 0.0008272 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        //[GCDemo (Allocation Failure) [PSYoungGen: 496K->504K(2560K)] 1173K->1189K(9728K), 0.0007003 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        //[Full GCDemo (Allocation Failure) [PSYoungGen: 504K->0K(2560K)] [ParOldGen: 685K->875K(7168K)] 1189K->875K(9728K), [Metaspace: 3282K->3282K(1056768K)], 0.0069323 secs] [Times: user=0.13 sys=0.00, real=0.01 secs]
        //[GCDemo (Allocation Failure) [PSYoungGen: 0K->0K(2560K)] 875K->875K(9728K), 0.0004569 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        //[Full GCDemo (Allocation Failure) [PSYoungGen: 0K->0K(2560K)] [ParOldGen: 875K->858K(7168K)] 875K->858K(9728K), [Metaspace: 3282K->3282K(1056768K)], 0.0073163 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
        //Heap
        //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        // PSYoungGen      total 2560K, used 81K [0x00000000ffd00000, 0x0000000100000000, 0x0000000100000000)
        //	at com.guanshi.season2.jvm.gc.HelloGc.main(HelloGc.java:33)
        //  eden space 2048K, 3% used [0x00000000ffd00000,0x00000000ffd14490,0x00000000fff00000)
        //  from space 512K, 0% used [0x00000000fff80000,0x00000000fff80000,0x0000000100000000)
        //  to   space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
        // ParOldGen       total 7168K, used 858K [0x00000000ff600000, 0x00000000ffd00000, 0x00000000ffd00000)
        //  object space 7168K, 11% used [0x00000000ff600000,0x00000000ff6d6b68,0x00000000ffd00000)
        // Metaspace       used 3341K, capacity 4496K, committed 4864K, reserved 1056768K
        //  class space    used 357K, capacity 388K, committed 512K, reserved 1048576K

        //-XX:SurvivorRatio=8

        //-XX:NewRatio=2

        //-XX:MaxTenuringThreshold=15

        //-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:NewRatio=2

    }
}
