package com.guanshi.season1.test2;

/**
 * @Description: 实例化顺序
 * @Author: songkai
 * @Date: 2021/2/4
 * @Version: 1.0
 */
public class InitializationSequence {

}

class Father{

    private int i = test();
    private  static  int j = method();

    static {
        System.out.println("(1)");
    }

    Father(){
        System.out.println("(2)");
    }

    {
        System.out.println("(3)");
    }

    public int test(){
        System.out.println("(4)");
        return 1;
    }

    public static int method(){
        System.out.println("(5)");
        return 1;
    }
}

class Son extends Father{


    private int i = test();
    private  static  int j = method();

    static {
        System.out.println("(6)");
    }

    Son(){
        System.out.println("(7)");
    }

    {
        System.out.println("(8)");
    }

    @Override
    public int test(){
        System.out.println("(9)");
        return 1;
    }

    public static int method(){
        System.out.println("(10)");
        return 1;
    }

    public static void main(String[] args) {

        /** 没有方法时执行
         (5)  父类静态方法
         (1)  父类静态代码块
         (10) 子类静态方法
         (6)  子类静态代码块
         初始化类会执行clinit,会初始化静态变量和静态代码块,谁在上面先执行谁
         初始化类,会先初始化其父类
        */

        Son son1 = new Son();
        System.out.println("-------");
        Son son2 = new Son();
        //输出
        /*
        (5)  父类静态方法
        (1)  父类静态代码块
        (10) 子类静态方法
        (6)  子类静态代码块
        (9)  子类成员方法
        (3)  父类构造代码块
        (2)  父类构造方法
        (9)  子类成员方法
        (8)  子类构造代码块
        (7)  子类构造方法
        总结:父类静态线性,再到子类静态
            父类成员变量、成员方法,构造方法
            实例化类
        -------
        (9)  子类成员方法
        (3)  父类构造代码块
        (2)  父类构造方法
        (9)  子类成员方法
        (8)  子类构造代码块
        (7)  子类构造方法

        全部总结:一个类需要创建实例需要先加载并初始化该类
                一个类初始化时需要先初始化父类

                一个类初始化执行的时clinit,包括静态变量和静态代码块
                静态变量和静态代码块从上到下执行

                实例初始化会执行init,包括成员变量和构造代码块,构造方法
                第一行如果不指定,就是super(),表示调用父类的构造方法
                成员变量和构造代码块,谁在上面先执行谁,最后执行构造方法
                最后执行构造方法.
        */
    }

}

