package com.xgsama.java.basis.jdbc;

/**
 * VolatileDemo
 *
 * @author xgSama
 * @date 2020/11/25 20:03
 */
public class VolatileDemo {

    public static void main(String[] args) {
        /*
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + "update number :" + myData.number);
        }, "AAA").start();

        while (myData.number == 0) {
        }

         */

        MyData myData = new MyData();

        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.add();
                }
            }, String.valueOf(i)).start();
        }

        // 等上面20个线程全部计算完后，再用main线程输出结果
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "\t numer " + myData.number);
    }
}

class MyData {
    volatile int number = 0;

    public void addTo60() {
        number = 60;
    }

    public void add() {
        number++;
    }
}
