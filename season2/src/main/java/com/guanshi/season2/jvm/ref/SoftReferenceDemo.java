package com.guanshi.season2.jvm.ref;

import java.lang.ref.SoftReference;

/**
 * @author poi 2021/2/14 11:24
 * @version 1.0
 * 2021/2/14 11:24
 * 软引用，内存充足，不回收，不充足，就回收
 * 缓存常用
 */
public class SoftReferenceDemo {
    /**
     * 内存足够不会被回收
     */
    public static void softRefMemoryEnough(){
        Object o1 = new Object();//强引用
        SoftReference<Object> reference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(reference.get());

        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(reference.get());

    }

    /**
     * 内存不够不会被回收
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void softRefMemoryNotEnough(){
        Object o1 = new Object();//强引用
        SoftReference<Object> reference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(reference.get());

        o1 = null;

        try {
            byte[] bs = new byte[30 * 1024 *1024] ;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            System.out.println(o1);//null
            System.out.println(reference.get());//null
        }


    }

    public static void main(String[] args) {
        softRefMemoryNotEnough();
    }
}
