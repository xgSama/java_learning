package com.xgsama.design.creational.singleton;

/**
 * StaticInnerClass
 * 静态内部类单例
 *
 * @author xgSama
 * @date 2021/3/22 10:16
 */
public class StaticInnerClass {

    private StaticInnerClass() {
    }

    private static class SingletonClassInstance {
        private static final StaticInnerClass instance = new StaticInnerClass();
    }

    // 只有真正调用getInstance() 才会加载静态内部类，加载类的时候是线程安全的，
    // instance是static final类型，保证了内存中只有这样一个实例存在，而且只能被赋值一次，从而保证了线程安全性
    // 兼备了并发高效调用和延迟加载的优势
    public static StaticInnerClass getInstance() {
        return SingletonClassInstance.instance;
    }
}
