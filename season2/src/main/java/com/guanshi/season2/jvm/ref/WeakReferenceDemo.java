package com.guanshi.season2.jvm.ref;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author poi 2021/2/14 11:24
 * @version 1.0
 * 2021/2/14 11:24
 * 弱引用-回收
 * 缓存常用
 */
public class WeakReferenceDemo {


    public static void main(String[] args) {
        Object o1 = new Object();//强引用
        WeakReference<Object> reference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(reference.get());

        o1 = null;
        System.gc();
        System.out.println("+++++++++++++");
        System.out.println(o1);
        System.out.println(reference.get());
        //java.lang.Object@3cd1a2f1
        //java.lang.Object@3cd1a2f1
        //+++++++++++++
        //null
        //null
    }
}
