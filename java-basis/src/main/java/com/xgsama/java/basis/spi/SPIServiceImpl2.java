package com.xgsama.java.basis.spi;

/**
 * SPIServiceImpl1
 *
 * @author : xgSama
 * @date : 2022/1/11 18:14:53
 */
public class SPIServiceImpl2 implements SPIService{
    static {
        System.out.println("I am service2 static");
    }
    @Override
    public void execute() {
        System.out.println("I am service2");
    }
}
