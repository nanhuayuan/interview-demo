package com.guanshi.season2.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author poi 2021/2/13 0:03
 * @version 1.0
 * 2021/2/13 0:03
 *
 * 题1：synchronized 机ock有什么区别？用新Lock有什么好处？你举例说说
 * 1原始的构成
 *      synchronized是关键字，属于JVM层面，
 *      monitorenter（底层是通过monitor对象来先成，其实wait/notify等方法也依赖imonitor对象只有同步块或方法小才能湖wait/notify等方法
 *      monitorexit
 *      Lock是具体类（java.util.concurrent.Locks.Lock）api层面的锁
 *
 *      2使儿方法
 *      synchronized 不需要用户去手动释放，当synchronized代码执行完成后系统会自动让线程释放锁，
 *      ReentrantLock则需要用户去手动释放锁，若没有释放，就可能导致出现死锁现象。
 *      需要lock()和unlock()方法配合try/finally语句块来完成。
 *
 *      3等待是个可中断
 *          synchronized 不可中断，抛出异常或者正前运行完成
 *          ReentrantLock 可中断，1.设置超时方法trylock（long timeout，Timeunit unit）
 *                              2.LockInterruptibly（）放代码块中，调用interrupt（）方法可中断
 *      4 加锁是否公平
 *          synchronized 非公平锁
 *          ReentrantLock两者都可以，默认非公平锁，构造方法可以传入boolean值，true为公平锁，false为非公平锁
 *      5锁绑定多个条/的condition
 *      synchronized没有
 *      ReentrantLock/用来实现分组唤醒需要唤醒的线程，可以精确唤醒，而不是像synchronized要么随机唤醒一个线程要么唤醒全部线程。
 *
 *  题目:多线程之可按顺序调用，实现A->8->C三个线程启动，要求如下：
 *      AA打5次，BB打印10次，CC打15次
 *      紧接着
 *      AA打5次，BB打印10次，CC打15次
 *      ————————-
 *      来10轮
 *
 */

/*class ShareResource{
    private int number = 1;//A-1;B-2,C-3
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void p5(){
        lock.lock();
        try{
            //1.判断
            while (number != 1){
                condition1.wait();
            }
            //2.干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i);
            }
            number = 2;
            //通知
            condition2.signal();

        } catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
    public void p10(){
        lock.lock();
        try{
            //1.判断
            while (number != 2){
                condition2.wait();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i);
            }
            number = 3;
            condition3.signal();

        } catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void p15(){
        lock.lock();
        try{
            //1.判断
            while (number != 3){
                condition3.wait();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i);
            }
            number = 1;
            condition1.signal();

        } catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}
public class SyncAndReentrantLockDemo {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                shareResource.p5();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                shareResource.p10();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                shareResource.p15();
            }
        }, "C").start();
    }
}*/


/******************* 资源类 *******************/
class ShareResource {
    // A 1   B 2   c 3
    private int number = 1;
    // 创建一个重入锁
    private Lock lock = new ReentrantLock();

    // 这三个相当于备用钥匙
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();


    public void print5() {
        //同步代码块：加锁
        lock.lock();
        try {
            // 判断
            while(number != 1) {
                // 不等于1，需要等待
                condition1.await();
            }

            // 干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + number + "\t" + i);
            }

            // 唤醒 （干完活后，需要通知B线程执行）
            number = 2;
            // 通知2号去干活了
            condition2.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            // 判断
            while(number != 2) {
                // 不等于1，需要等待
                condition2.await();
            }

            // 干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + number + "\t" + i);
            }

            // 唤醒 （干完活后，需要通知C线程执行）
            number = 3;
            // 通知2号去干活了
            condition3.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            // 判断
            while(number != 3) {
                // 不等于1，需要等待
                condition3.await();
            }

            // 干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + number + "\t" + i);
            }

            // 唤醒 （干完活后，需要通知C线程执行）
            number = 1;
            // 通知1号去干活了
            condition1.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/******************* 测试类 *******************/
public class SyncAndReentrantLockDemo {

    public static void main(String[] args) {

        ShareResource shareResource = new ShareResource();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print5();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print10();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print15();
            }
        }, "C").start();
    }
}
