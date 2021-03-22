package com.xgsama.design.structural.proxy;

import java.lang.reflect.Proxy;

/**
 * JDKProxy
 *
 * @author xgSama
 * @date 2021/3/22 10:34
 */
public class JDKProxyFactory {

    // 维护一个目标对象
    private Object target;

    // 构造器，对target 进行初始化
    public JDKProxyFactory(Object target) {
        this.target = target;
    }

    // 给目标对象生成一个代理对象
    public Object getProxyInstance() {
        /* ClassLoader loader: 指定当前目标对象使用的类加载器，获取加载器的方法固定
         * Class<?>[] interfaces：目标对象实现的接口类型，使用泛型的方式确认类型
         * InvocationHandler h：事件处理，执行目标对象方法时，会触发事件处理器方法，
         * 会把当前执行的目标对象方法作为一个参数传入
         */
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("JDK代理开始");
                    // 反射机制调用目标对象方法
                    Object returnVal = method.invoke(target, args);

                    System.out.println("JDK代理提交");
                    return returnVal;
                });
    }
}
