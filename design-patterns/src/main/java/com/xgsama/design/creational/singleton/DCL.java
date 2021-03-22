package com.xgsama.design.creational.singleton;

/**
 * DCL：双重检测锁单例模式
 *
 * @author xgSama
 * @date 2021/3/22 10:08
 */
public class DCL {

    // volatile禁止指令重排，预防在特殊情况下会获取到空对象
    public static volatile DCL instance = null;

    private DCL() {
    }

    public static DCL getInstance() {
        if (instance == null) {
            synchronized (DCL.class) {
                if (instance == null) {
                    instance = new DCL();
                }
            }
        }

        return instance;
    }
}
