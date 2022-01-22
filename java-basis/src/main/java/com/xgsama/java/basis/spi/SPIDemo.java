package com.xgsama.java.basis.spi;

import sun.misc.Service;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * SPIDemo
 *
 * @author : xgSama
 * @date : 2022/1/11 11:13:39
 */
public class SPIDemo {
    public static void main(String[] args) throws ClassNotFoundException {
//        Iterator<SPIService> providers = Service.providers(SPIService.class);
//        while (providers.hasNext()) {
//            providers.next().execute();
//        }

//        ServiceLoader<SPIService> load = ServiceLoader.load(SPIService.class);

        ClassLoader loader = ClassLoader.getSystemClassLoader();
        Class.forName("com.xgsama.java.basis.spi.SPIServiceImpl1", true, loader);
        ClassLoader.getSystemClassLoader().loadClass("com.xgsama.java.basis.spi.SPIServiceImpl1");

    }
}
