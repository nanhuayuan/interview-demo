package com.guanshi.season2.thread;

import lombok.Getter;

/**
 * @author poi 2021/2/12 20:07
 * @version 1.0
 * 2021/2/12 20:07
 */
public enum CountyEnum {
    ONE(1,"齐"),rwo(2,"楚"),THREE(3,"燕"),FOUR(4,"赵"),FIVE(5,"魏"),SIX(6,"韩");
    
    @Getter private Integer retCode;
    @Getter private String retMessage;
    
    CountyEnum(Integer retCode, String retMessage){
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public  static CountyEnum forEach_CountyEnum(int code){
        CountyEnum[] values = CountyEnum.values();
        for (CountyEnum value : values) {
            if (code == value.getRetCode()){
                return value;
            }
        }
        return null;
    }
}
