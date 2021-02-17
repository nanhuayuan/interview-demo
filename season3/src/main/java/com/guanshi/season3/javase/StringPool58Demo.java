package com.guanshi.season3.javase;

/**
 * @author poi 2021/2/16 22:25
 * @version 1.0
 * 2021/2/16 22:25
 * intern()方法:如果常量池中有，则返回该引用;如果没有，则添加到常量池中再返回引用
 *
 * 2.4.3方法区和运行时常量池溢出
 * 由于运行时常量池是方法区的一部分，所以这两个区域的溢出测试可以放到一起进行。
 * 前面曾经提到HotSpot从JDK7开始逐步“去永久代”的计划，并在JDK8中完全使用元空间来代替永久代的背景故事，
 * 在此我们就以测试代码来观察一下，使用“永久代”还是“元空间”来实现方法区，对程序有什么实际的影响。
 *
 *
 * String:intern（）是一个本地方法，
 * 它的作用是如果字符串常量池中已经包含一个等于此String对象的字符串，则返回代表池中这个字符串的String对象的引用：
 * 否则，会将此String对象包含的字符串添加到常量池中，并且返回此String对象的引用。
 * 在JDK6或更早之前的HotSpot虚拟机中，常量池都是分配在永久代中，
 * 我们可以通过-XX:PermSize和-XX:MaxPermSize限制永久代的大小，即可间接限制其中常量池的容量
 */
public class StringPool58Demo {

    public static void main(String[] args) {
        String str1 = new StringBuilder("58").append("tongcheng").toString();
        System.out.println(str1);
        System.out.println(str1.intern());
        System.out.println(str1 == str1.intern());//false
        //intern()

        System.out.println("========分割线============");

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2);
        System.out.println(str2.intern());
        System.out.println(str2 == str2.intern());//true



    }
}
