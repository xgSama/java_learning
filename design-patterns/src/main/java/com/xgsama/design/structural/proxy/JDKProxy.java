package com.xgsama.design.structural.proxy;

/**
 * JDKProxy
 *
 * @author xgSama
 * @date 2021/3/22 10:42
 */
public class JDKProxy {

    public static void main(String[] args) {
        ITeacherDao target = new TeacherDao();

        ITeacherDao proxyInstance = (ITeacherDao) new JDKProxyFactory(target).getProxyInstance();

        System.out.println("proxyInstance=" + proxyInstance);
        // proxyInstance=com.ano.design.proxy.TeacherDao@7ef20235
        System.out.println(proxyInstance.getClass());
        // class com.sun.proxy.$Proxy0

        proxyInstance.teach();

        proxyInstance.sayHello("name");
    }
}
