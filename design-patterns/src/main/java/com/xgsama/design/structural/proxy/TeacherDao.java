package com.xgsama.design.structural.proxy;

/**
 * TeacherDao
 * 需要被代理的类
 *
 * @author xgSama
 * @date 2021/3/22 10:29
 */
public class TeacherDao implements ITeacherDao {

    public void teach() {
        System.out.println("教师授课中。。。");
    }

    @Override
    public void sayHello(String name) {
        System.out.println("hello," + name);
    }
}
