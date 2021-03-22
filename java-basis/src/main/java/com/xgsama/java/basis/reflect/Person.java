package com.xgsama.java.basis.reflect;

import com.xgsama.java.basis.annotation.MyAnnotation;

/**
 * Person
 *
 * @author xgSama
 * @date 2020/11/22 13:51
 */
@MyAnnotation("hello")
public class Person {
    private String name;
    public int age;

    public Person() {
    }

    private Person(String name) {
        this.name = name;
    }


    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }


    @MyAnnotation("hello")
    public void show() {
        System.out.println("你好，我是一个人");
    }


    private String  showNation(String nation) {
        System.out.println("我的国籍是：" + nation);
        return nation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
