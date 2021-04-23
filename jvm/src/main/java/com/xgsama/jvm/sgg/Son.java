package com.xgsama.jvm.sgg;

/**
 * 用于方法调用中的虚方法，非虚方法测试
 *
 * @author xgSama
 * @date 2021/4/23 16:17
 */
public class Son extends Father {

    public Son() {
        super();
    }

    public Son(int age) {
        this();
    }

    public static void showStatic(String str) {
        System.out.println("son " + str);
    }

    private void showPrivate(String str) {
        System.out.println("son private " + str);
    }

    public void show() {
        // invokesttaic
        showStatic("java");
        // invokesttaic
        super.showStatic("good!");

        // invokespecial
        showPrivate("hello");
        // invokespecial
        super.showCommon();

        // invokevirtual
        showFinal();    // final修饰 => 非虚方法
        // invokevirtual
        showCommon();   // 普通方法 => 虚方法

        MethodInterface in = null;
        // invokeinterface
        in.methodA();
    }

    public void info() {
    }

    public void display(Father f) {
        f.showCommon();
    }

    public static void main(String[] args) {
        Son son = new Son();
        son.show();
    }

}

class Father {
    public Father() {
        System.out.println("Father的构造器");
    }

    public static void showStatic(String str) {
        System.out.println("father " + str);
    }

    public final void showFinal() {
        System.out.println("father show final");
    }

    public void showCommon() {
        System.out.println("father 普通方法");
    }
}

interface MethodInterface {

    void methodA();
}
