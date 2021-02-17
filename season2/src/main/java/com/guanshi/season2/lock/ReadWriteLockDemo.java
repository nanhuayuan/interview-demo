package com.guanshi.season2.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class  Mycache{
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();


    public  void put (String key, Object value){
        rwLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t 正在写入:" + key);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成:" + key);
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            rwLock.writeLock().unlock();
        }

    }

    public Object get (String key){
        rwLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t 正在读取:" + key);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 获得完成:" + result);
            return result;
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            rwLock.readLock().unlock();
        }
        return  null;
    }

    public void clearMap(){
        map.clear();
    }
}

/**
 * @author poi 2021/2/12 17:13
 * @version 1.0
 * 2021/2/12 17:13
 *
 * 多个能程同时读一个资源类没有在何问题，所以为了满足并发量，读取共宾资源应该可以同时进行。
 * 但是
 * 如果有一个线程组去写共享资源来，就不应该再有其它线程可以对该资源进行读或写小总统：
 * 读-读能共存
 * 读-写不能共存
 * 写写不能共存
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {

        Mycache mycache = new Mycache();

        for (int i = 0; i < 5; i++) {
            final String temp = String.valueOf(i);
            new Thread(() -> {
               mycache.put(temp, temp);
            }, temp).start();
        }
        for (int i = 0; i < 5; i++) {
            final String temp = String.valueOf(i);
            new Thread(() -> {
                mycache.get(temp);
            }, temp).start();
        }

    }
}
