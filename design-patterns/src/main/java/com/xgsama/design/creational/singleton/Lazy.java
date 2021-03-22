package com.xgsama.design.creational.singleton;

/**
 * Lazy：懒汉式单例模式
 *
 * @author xgSama
 * @date 2021/3/22 10:20
 */
public class Lazy {

    // 初始化类时，不初始化这个对象，真正用的时候再创建
    private static Lazy instance;

    // 私有化构造器
    private Lazy() {
    }

    // 资源利用率高了，每次调用getInstance()都要同步，并发效率低
    public static synchronized Lazy getInstance() {
        if (instance == null) {
            instance = new Lazy();
        }

        return instance;
    }
}
