package com.guanshi.season2.jvm.oom;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author poi 2021/2/15 11:40
 * @version 1.0
 * 2021/2/15 11:40
 * Java 8及之后的版本使用Metaspace来替代永久代。
 *
 * Metaspace是方法区在Hotspot中的实现，它与持久代最大的区别在于:Metaspace并不在虚拟机内存中而是使用本地内存
 * 也即在java8中，classe metadata（the virtual machines internal presentation of Java class），被存储在叫做
 * Metaspace/inative memory
 * 永久代（Java8后被元空间Metaspace收代了）存放了以下信息：
 * 虚拟机加载的类信息
 * 常量池
 * 静态变量
 * 即时编译后的代码
 *
 * 模拟Metaspace空间溢出，我们不断生成类往元空间灌，类占据的的空间总是会超过Metaspace指定的空可大小的
 */
public class MetaspaceDemo {

    static class OOMTest{
    }

    public static void main(String[] args) {
        //-XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
        int i  = 0;
        try {
            while (true){
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o,args);
                    }
                });
                enhancer.create();
            }
        } catch (Throwable e){
            System.out.println("多少次后发生了异常:" + i);
            e.printStackTrace();
        } finally {

        }
        //java.lang.OutOfMemoryError: Metaspace
        //多少次后发生了异常:305
    }
}
