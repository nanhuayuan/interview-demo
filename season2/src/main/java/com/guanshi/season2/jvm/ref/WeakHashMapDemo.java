package com.guanshi.season2.jvm.ref;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @author poi 2021/2/14 11:24
 * @version 1.0
 * 2021/2/14 11:24
 * 弱引用-回收
 * 缓存常用
 */
public class WeakHashMapDemo {

    public static void main(String[] args) {
        myHashMap();
        myweakHashMap();
    }

    public static void myHashMap() {
        HashMap<Integer, String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "HashMap";

        map.put(key,value);
        System.out.println(map);

        key = null;
        System.out.println("key置空");
        System.out.println(map);

        System.out.println("GCDemo");
        System.gc();
        System.out.println(map);

    }

    public static void myweakHashMap() {
        System.out.println("******************WeakHashMap*****************");
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key = new Integer(2);
        String value = "WeakHashMap";

        map.put(key,value);
        System.out.println(map);

        key = null;
        System.out.println("key置空");
        System.out.println(map);

        System.out.println("GCDemo");
        System.gc();
        System.out.println(map);

    }
}
