package com.guanshi.season2.jvm.oom;

import java.util.Random;

/**
 * @author poi 2021/2/14 18:16
 * @version 1.0
 * 2021/2/14 18:16
 */
public class JavaHeapSpace {

    public static void main(String[] args) {
        //-Xms5m -Xmx5m -XX:+PrintGCDetails
        String str = "guanshi";
        /*while (true){
            str = str + new Random ().nextInt(1111111)+ + new Random ().nextInt(22222222);
            str.intern();
        }*/
        //10M
        //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        byte[] bytes = new byte[10 * 1024 * 1024];
    }
}
