package com.guanshi.season2.jvm.oom;

/**
 * @author poi 2021/2/14 18:07
 * @version 1.0
 * 2021/2/14 18:07
 */
public class StackOverflowErrorDemo {

    public static void main(String[] args) {
        stackOverflowError();
    }

    public static void stackOverflowError() {
        stackOverflowError();//Exception in thread "main" java.lang.StackOverflowError
    }
}
