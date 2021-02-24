package com.guanshi.season2.thread.queue;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author poi 2021/2/12 20:46
 * @version 1.0
 * 2021/2/12 20:46
 * ArrayBlockingQueue：是一个基于数组结构的有界阻塞队列，此队列按FIFO（先进先出）原则对元素进行排序。
 * LinkedBlockingQueue：一个基于链表结构的阻塞队列，此队列接FIFO（先进先出）推序元素，吞吐量进常要高/ArrayBlockingQueue。
 * SynchronousQueue：一个不存储元素的阻塞队列。每个插入操作必须等到另一个继程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于
 *
 * 1.队列 :先到先得
 * 2.阻塞队列
 * 当阻塞队列是空时，从队列中获取元素的操作将会被阻塞。
 * 当阻塞队列是满时，往队列里添加元素的操作将会被阻塞。
 * ·    试图从空的阻塞队列中获取元素的线程将会被阻塞，直到其他的线程往空的队列插入新的元素。
 *      同样
 *      试图往已满的阻塞队列中添加新元素的线程同样也会被阻塞，直到其他的线程从列中移除一个或者多个元素或者完全清空队列后使队列重新变得空闲起来并后续新增
 *      2.1 好的一面
 *          在多线程领域：所谓阻塞，在某些情况下会挂起线程（即阻塞），一旦条件满足，被挂起的线程又会自动被唤醒
 *          为什么需要BlockingQueue
 *          好处是我们不需要关心什么时候需要阻塞线程，什么时候需要唤醒线程，因为这一切BlockingQueue都给你一手包办了
 *          在concurrent包发布以前，在多线程环境下，我们每个程序员都必须去自己控制这些细节，
 *          尤其还要兼顾效率和线程安全，而这会给我们的程序带来不小的复杂度。
 *      2.1不得不阻塞，如何管理
 *
 *
 *      ArrayBlockingaueue（重要）：由数组结构组成的有界阻塞队列。
 *      LinkedBlockingQueue（重要）：由链结构组成的有界（但大小默认值为Integer.MAX_VALUE）阻塞队列
 *      PriorityBlockingQueue：支持优先级排序的无界阻塞队列。
 *      Delayaueue：使用优先级队列实现的延迟无界阻塞队列。
 *      SynchronousQueue（重要）：不存储元素的阻塞队列，也即单个元素的队列。每一个put操作必须要等待一个take操作，否则不能继续添加元素，反之亦然。
 *      LinkedTransferQueue：由链表结构组成的无界阻塞队列。
 *      LinkedBlockingpeque：由链表结构组成的双向阻塞队列
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws Exception{

        List list = null;

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        // ************************抛异常********************
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        //队列满了，抛异常
        //Exception in thread "main" java.lang.IllegalStateException: Queue full
        //System.out.println(blockingQueue.add("x"));

        //队列是不是空，队首元素是
        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

        //抛异常 队列是空的
        //Exception in thread "main" java.util.NoSuchElementException
        //System.out.println(blockingQueue.remove());

        System.out.println("***********抛异常结束****************");

        BlockingQueue<String> blockingQueue2 = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue2.offer("a"));
        System.out.println(blockingQueue2.offer("b"));
        System.out.println(blockingQueue2.offer("c"));

        //队列满了，不抛异常
        System.out.println(blockingQueue2.offer("x"));//false

        //探测顶端元素
        System.out.println(blockingQueue2.peek());

        System.out.println(blockingQueue2.poll());
        System.out.println(blockingQueue2.poll());
        System.out.println(blockingQueue2.poll());

        //不抛异常 队列是空的
        System.out.println(blockingQueue2.poll());//null

        System.out.println("***********不抛异常结束****************");

        BlockingQueue<String> blockingQueue3 = new ArrayBlockingQueue<>(3);

        blockingQueue3.put("a");
        blockingQueue3.put("b");
        blockingQueue3.put("c");

        //满了会一直往队列put，直到成功或者中断
        blockingQueue3.put("x");//false



        System.out.println(blockingQueue3.take());
        System.out.println(blockingQueue3.take());
        System.out.println(blockingQueue3.take());

        //
        System.out.println(blockingQueue3.take());
        //System.out.println(blockingQueue3.take());//会阻塞线程

        System.out.println("***********阻塞结束****************");

    }
}
