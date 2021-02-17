package com.guanshi.season2.jvm.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * @author poi 2021/2/14 11:24
 * @version 1.0
 * 2021/2/14 11:24
 *
 * 虚引用
 *
 * java提供了4种引月类型，在垃级回收的时候，都有自已各自的特点。
 * ReferenceQueue是用来配合引用工作的，没有ReferenceQueue一样可以运行。
 *
 * 创建引用的时候可以指定关联的队列，当GC释放矿象内存的时能，会将引用加入到引用队列，
 * 如果程序发现某个虚引用已经酸加入到引用队列，那么就可以在所引用的对象的内存被问收之前深收必要的行动
 * 当于是一种进知机制。
 *
 * 当关联的引期队列力有数据的时候，意味着引用指向的维内存中的对象被回收。通过这种方式，JVm允许我们在社象被销酸后，
 * 做一些我们自已想做的事情。
 */
public class PhantomReferenceDemo {


    public static void main(String[] args) {
        Object o1 = new Object();//强引用
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> reference = new PhantomReference<>(o1, referenceQueue);

        System.out.println(o1);
        System.out.println(reference.get());//null
        System.out.println(referenceQueue.poll());//null

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
        //null
        //null
        //**********置空之后**************
        //null
        //null
        //null
        //**********置空,GC之后**************
        //null
        //null
        //java.lang.ref.PhantomReference@2f0e140b
    }
}
