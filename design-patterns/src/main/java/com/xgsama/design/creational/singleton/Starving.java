package com.xgsama.design.creational.singleton;

/**
 * Starving：饿汉式单例
 *
 * @author xgSama
 * @date 2021/3/22 10:20
 */
public class Starving {
    // 类初始化时，立即加载这个对象！
    // 加载类时，天然的是线程安全的
    private static Starving instance = new Starving();

    // 私有化构造器
    private Starving() {
    }

    // 方法没有同步，调用效率高
    public static Starving getInstance() {
        return instance;
    }
}
