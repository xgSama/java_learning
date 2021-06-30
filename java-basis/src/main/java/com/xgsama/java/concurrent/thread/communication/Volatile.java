package com.xgsama.java.concurrent.thread.communication;


/**
 * Volatile
 *
 * @author xgSama
 * @date 2021/6/30 19:30
 */
@SuppressWarnings({"DuplicatedCode"})
public class Volatile {

    public static void main(String[] args) {

        // 内存可见性测试
        testVisibility();
        // 不保证原子性测试
        testAtomicity();

    }

    public static void testVisibility() {
        // 保证内存可见性的示例
        Child.setA(1);
        new Thread(() -> {
            try {
                // 保证先让主线程读到A的值后再写入
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 修改A的值
            Child.setA(3);
            System.out.println("child-thread: read variable A - " + Child.A);
        }).start();
        System.out.println("main-thread: read variable A - " + Child.A);
        // 如果无法感知，主线程一直循环
        while (Child.A == 1) {
        }
        System.out.println("main-thread: read variable A - " + Child.A);
    }

    public static void testAtomicity() {
        Num myData = new Num();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    myData.add();
                }
            }, String.valueOf(i)).start();
        }

        // 等上面20个线程全部计算完后，再用main线程输出结果
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        // 不保证原子性，最终结果有误
        System.out.println("不保证原子性导致的错误结果 number： " + myData.number);
    }


    static class Num {

        volatile int number = 0;

        // 原子性问题，对比有无synchronized结果
        public /* synchronized */ void  add() {
            number++;
        }
    }

    static class Child {
        // 对比有无volatile的差别
        static volatile int A = 0;

        public static void setA(int a) {
            A = a;
        }
    }
}
