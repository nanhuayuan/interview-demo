package com.guanshi.season2.lock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author poi 2021/2/11 16:50
 * @version 1.0
 * 2021/2/11 16:50
 */
public class T1 {

    volatile int n = 0;
    public  void  add(){
        n++;
    }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
    }
}
