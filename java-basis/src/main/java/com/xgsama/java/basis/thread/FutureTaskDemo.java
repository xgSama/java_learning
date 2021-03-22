package com.xgsama.java.basis.thread;


import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * FutureTaskDemo
 *
 * @author xgSama
 * @date 2021/1/19 9:45
 */
public class FutureTaskDemo {
    public static void main(String[] args) {
        Callable<Integer> call = () -> {
            System.out.println("正在计算结果...");
            Thread.sleep(3000);
            return 1;
        };

        FutureTask<Integer> task = new FutureTask<>(call);

        Thread thread = new Thread(task);
        thread.start();

        // do something
        for (int i = 0; i < 20; i++) {

            System.out.println(" 干点别的...");
        }

        Integer result = null;


        try {
            result = task.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("拿到的结果为：" + result);
    }
}
