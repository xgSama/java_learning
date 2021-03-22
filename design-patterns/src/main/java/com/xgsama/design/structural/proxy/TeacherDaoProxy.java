package com.xgsama.design.structural.proxy;

/**
 * TeacherDaoProxy
 * 静态代理模式的代理类
 *
 * @author xgSama
 * @date 2021/3/22 10:30
 */
public class TeacherDaoProxy implements ITeacherDao {
    // 目标对象，通过接口来聚合
    private ITeacherDao target;

    // 构造器
    public TeacherDaoProxy(ITeacherDao target) {
        this.target = target;
    }

    public void teach() {
        System.out.println("代理开始。。。");
        target.teach();
        System.out.println("提交。。。");
    }

    @Override
    public void sayHello(String name) {

    }
}
