package com.guanshi.season2.jvm.gcroots;

/**
 * @author poi 2021/2/13 22:59
 * @version 1.0
 * 2021/2/13 22:59
 * GCDemo Root
 * 1.虚拟机钱（栈帧中的的变量表）中引用的对象；
 * 2.方法区中的类静态属性引用的对象；
 * 3.方法区中常量引用的对象；
 * 4.本地方法线JNI（即一般说Nivative方法）中引用的对象
 */
public class GCRootDemo {
    private  byte[] bayeArray = new byte[100 * 1024 * 1024];
    //private static GCRootDemo2 t2;
    //private static final GCRootDemo3 t3;
    public static void m1(){

    }

    public static void main(String[] args) {

    }
}
