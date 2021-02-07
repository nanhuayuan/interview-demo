package com.guanshi.season1.test1;

/**
 * @Description: 运算符计算
 * @Author: songkai
 * @Date: 2021/2/4
 * @Version: 1.0
 */
public class Operator {

    public static void main(String[] args) {
        int i = 1;
        i = i++;
        System.out.println("i = i++: " + i);
        int j = i++;
        System.out.println("int j = i++: " + i);
        System.out.println("int j = i++: " + j);
        int k = i + ++i * i++;
        System.out.println("i:" + i);
        System.out.println("j:" + j);
        System.out.println("k:" + k);

    }
}
