package com.guanshi.season3;

import com.guanshi.season3.spring.aop.CalcService;
/*import org.junit.Test;
import org.junit.runner.RunWith;*/
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;

@SpringBootTest
//@RunWith(SpringRunner.class)//1.5.9
public class TestsAop {

    @Autowired
    private CalcService calcService;

    /**
     * 正常
     */
    @Test
    public void contextLoads() {
        System.out.println("spring版本："+ SpringVersion.getVersion()+"\t"+"SpringBoot版本："+ SpringBootVersion.getVersion());

        System.out.println();

        calcService.div(10,2);

        /*我是环绕通知之前AAA
        ******** @Before我是前置通知MyAspect
        =========>CalcServiceImpl被调用了，计算结果:5
        我是环绕通知之后BBB
        ******** @After我是后置通知
        ********@AfterReturning我是返回后通知*/


        /**
         spring版本：5.2.8.RELEASE	SpringBoot版本：2.3.3.RELEASE

         我是环绕通知之前AAA
         ******** @Before我是前置通知MyAspect
        =========>CalcServiceImpl被调用了，计算结果:5
         ********@AfterReturning我是返回后通知
         ******** @After我是后置通知
        我是环绕通知之后BBB
         *
         */
    }

    /**
     * 异常
     */
    @Test
    public void contextLoads2() {
        System.out.println("spring版本："+ SpringVersion.getVersion()+"\t"+"SpringBoot版本："+ SpringBootVersion.getVersion());

        System.out.println();

        calcService.div(10,0);

        /*
        我是环绕通知之前AAA
        ******** @Before我是前置通知MyAspect
        ******** @After我是后置通知
        ********@AfterThrowing我是异常通知

        java.lang.ArithmeticException: / by zero
        * */

        /**
         spring版本：5.2.8.RELEASE	SpringBoot版本：2.3.3.RELEASE

         我是环绕通知之前AAA
         ******** @Before我是前置通知MyAspect
         ********@AfterThrowing我是异常通知
         ******** @After我是后置通知

        java.lang.ArithmeticException: / by zero
         */
    }

}
