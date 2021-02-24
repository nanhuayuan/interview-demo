package com.guanshi.season2.dclsingleton;

/**
 * @author poi 2021/2/10 22:56
 * @version 1.0
 * 2021/2/10 22:56
 */
public class SingletonDemo {

    private static volatile SingletonDemo  instance =  null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法" );
    }

    /*public static SingletonDemo getInstance(){
        if (instance == null){
            instance = new SingletonDemo();
        }
        return  instance;
    }*/

    //DCL 双端检索机制（double check lock）
    //DCL（双端检锁）机制不一定线程安全，原因是有指令重排序的存在，加入volatile可以禁止指令重排
    // 原因在于某一个线程执行到第一次检测，读取到的instance不为null时，instance的引用对象可能没有完成初始化
    // instance = new SingletonDemo（）；
    // 可以分为以下3步完成（伪代码）
    //memory=allocate（）；// 1.分配对象内存空间
    // instance（memory）；// 2.初始化对象
    // instance=memory；//3.设置instance指向刚分配的内存地址，此时instance！null

    //步骤2和步骤3不存在数据依赖关系，而且无论重排前还是重排后程序的执行结果在单线程中并没有改变，因此这种重排优化是允许的。
    //memory=allocate（）；//1.分配对象内存空间
    //instance=memory；//3.设置instance指向刚分配的内存地址，此时instance！=null，但是对象还没有初始化完成！
    //instance（memory）；//2.初始化对象
    //但是指令重排只会保证串行语义的执行的一致性（单线程），但并不会关心多线程间的语义一致性。
    //所以当一条线程访问instance不为nulll时，由于instance实例未必已初始化完成，也就造成了线程安全问题。

    public static SingletonDemo getInstance(){
        if (instance == null){
            synchronized (SingletonDemo.class){
                if (instance == null){
                    instance = new SingletonDemo();
                }
            }
        }
        return  instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                getInstance();
            }, String.valueOf(i)).start();

        }
    }
}
