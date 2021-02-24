package com.guanshi.season3.spring.aop.impl;

import com.guanshi.season3.spring.aop.CalcService;
import org.springframework.stereotype.Service;

/**
 * @author poi 2021/2/22 20:58
 * @version 1.0
 * 2021/2/22 20:58
 */
@Service
public class CalcServiceImpl implements CalcService {

    @Override
    public int div(int x, int y) {
        int reslut = x / y;
        System.out.println("=========>CalcServiceImpl被调用了，计算结果:" + reslut);
        return 0;
    }
}
