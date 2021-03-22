package com.xgsama.design.structural.proxy;

/**
 * StaticProxy：静态代理
 * 优点：在不修改目标对象的前提下，能通过代理对象对目标功能扩展
 * 缺点：因为代理对象需要与目标对象实现一样的接口，所以会有很多代理类，一旦接口方法变化，目标对象与代理对象都要维护
 *
 * @author xgSama
 * @date 2021/3/22 10:27
 */
public class StaticProxy {

    public static void main(String[] args) {
        // 创建目标对象（被代理的对象）
        TeacherDao teacherDao = new TeacherDao();

        // 创建代理对象
        TeacherDaoProxy teacherDaoProxy = new TeacherDaoProxy(teacherDao);

        // 通过代理对象，调用被代理对象的方法
        // 执行的是代理对象的方法，代理对象去调用目标对象的方法
        teacherDaoProxy.teach();
    }


}
