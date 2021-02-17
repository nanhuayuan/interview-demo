package com.guanshi.season2.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author poi 2021/2/11 15:09
 * @version 1.0
 * 2021/2/11 15:09
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) {

        mapNotSafe();
    }
    public static void mapNotSafe(){
        //Map<String, String> map = new HashMap<>();
        //Map<String, String> map = Collections.synchronizedMap(new HashMap());
        Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() ->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
        // 报错java.util.ConcurrentModificationException

        //1.故障现象
        //  java.util.ConcurrentModificationException
        //
        //2.原因
        //多线程并发修改导致出错

        //3.解决方案
        // 3.1 Map<String, String> map = Collections.synchronizedMap(new HashMap());
        // 3.2 Map<String, String> map = new ConcurrentHashMap<>();

        //4.优化建议（同样地错误不再犯）
    }

    public static void setNotSafe(){
        /*Set<String> set = new HashSet<>();

        for (int i = 0; i < 3; i++) {
            new Thread(() ->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }*/
        /*[744cac68]
        [744cac68]
        [744cac68]*/


        //Set<String> set = new HashSet<>();
        //Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet();

        for (int i = 0; i < 30; i++) {
            new Thread(() ->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
        // 报错java.util.ConcurrentModificationException

        //1.故障现象
        //  java.util.ConcurrentModificationException
        //
        //2.原因
        //多线程并发修改导致出错

        //3.解决方案
        // 3.1 Set<String> set = Collections.synchronizedSet(new HashSet<>());
        // 3.2 Set<String> set = new CopyOnWriteArraySet();

        //4.优化建议（同样地错误不再犯）
    }

    public static void listNotSafe(){
        //List<String> list = Arrays.asList("a","b","c");

        /*for (String s : list) {
            System.out.println(s);
        }*/


        /*List<String> list = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            new Thread(() ->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }*/
        /*[316bb0cd, 1432162b]
        [316bb0cd, 1432162b, 419c8055]
        [316bb0cd, 1432162b]*/

        //List<String> list = new ArrayList<>();
        //List<String> list = new Vector<>();
        //List<String> list = Collections.synchronizedList( new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() ->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        //1.故障现象
        //  java.util.ConcurrentModificationException
        //
        //2.原因
        //多线程并发修改导致出错

        //3.解决方案
        // 3.1 new Vector<>();
        // 3.2List<String> list = Collections.synchronizedList( new ArrayList<>());
        // 3.3List<String> list = new CopyOnWriteArrayList<>();

        //4.优化建议（同样地错误不再犯）
    }
}
