package com.guanshi.season2.jvm.ref;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author poi 2021/2/14 11:24
 * @version 1.0
 * 2021/2/14 11:24
 * 弱引用-回收
 * 缓存常用
 */
public class ReferenceQueueDemo {

    public static void main(String[] args) {

        Object o1 = new Object();//强引用
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> reference = new WeakReference<>(o1, referenceQueue);

        System.out.println(o1);
        System.out.println(reference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("**********置空之后**************");
        o1 = null;
        System.out.println(o1);
        System.out.println(reference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("**********置空,GC之后**************");
        System.gc();
        try { TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println(o1);
        System.out.println(reference.get());
        System.out.println(referenceQueue.poll());

        //java.lang.Object@3cd1a2f1
        //java.lang.Object@3cd1a2f1
        //null
        //**********置空之后**************
        //null
        //java.lang.Object@3cd1a2f1
        //null
        //**********置空,GC之后**************
        //null
        //null
        //java.lang.ref.WeakReference@2f0e140b
    }
}
