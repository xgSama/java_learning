package com.xgsama.design.structural.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLibProxyFactory
 *
 * @author xgSama
 * @date 2021/3/22 10:47
 */
public class CGLibProxyFactory implements MethodInterceptor {

    // 维护一个目标对象
    private Object target;

    public CGLibProxyFactory(Object object) {
        this.target = object;
    }

    // 返回一个代理对象，是target对象的代理对象
    public Object getProxyInstance() {
        // 创建一个工具类
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(target.getClass());
        // 设置回调函数
        enhancer.setCallback(this);
        /*  也可以这么写 不用实现MethodInterceptor接口了
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            System.out.println("Cglib代理模式======开始" + "\n" + "前置增强");

            //直接调用目标对象的方法（目标方法执行方式1）
            Object invoke = method.invoke(target, args);

            System.out.println("Cglib代理模式======提交" + "\n" + "后置增强");

            return invoke;
        });
        */
        // 创建子类对象，即代理对象
        return enhancer.create();

    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        System.out.println("Cglib代理模式======开始" + "\n" +
                "前置增强");

        // 直接调用目标对象的方法（目标方法执行方式1）
        Object invoke = method.invoke(target, args);

        // 调用代理代理对象的父类方法，相当于间接调用目标对象的方法（目标方法执行方式2）
        // Object invoke = methodProxy.invokeSuper(proxy, args);

        System.out.println("后置增强" + "\n" +
                "Cglib代理模式======提交");

        return invoke;
    }
}
