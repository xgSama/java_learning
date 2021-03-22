package com.xgsama.design.structural.proxy;

/**
 * CGLibProxy
 *
 * @author xgSama
 * @date 2021/3/22 10:50
 */
public class CGLibProxy {

    public static void main(String[] args) {
        TeacherDao target = new TeacherDao();
        TeacherDao proxyInstance =
                (TeacherDao) new CGLibProxyFactory(target).getProxyInstance();

        proxyInstance.teach();

    }
}
