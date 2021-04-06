package com.xgsama.java.basis.juc;

/**
 * PrintABCUseSyn
 *
 * @author xgSama
 * @date 2021/3/26 10:34
 */
public class PrintABCUseSyn {

    private static boolean flagA = true;
    private static boolean flagB = false;
    private static boolean flagC = false;

    public static void main(String[] args) {

        final Object lock = new Object();

        new Thread(() -> {
            for (int i = 0; i < 10;) {
                synchronized (lock) {
                    if (flagA) {
                        System.out.print("A");
                        flagA = false;
                        flagB = true;
                        flagC = false;
                        lock.notifyAll();
                        i++;
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10;) {
                synchronized (lock) {
                    if (flagB) {
                        System.out.print("B");
                        flagA = false;
                        flagB = false;
                        flagC = true;
                        lock.notifyAll();
                        i++;
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; ) {
                synchronized (lock) {
                    if (flagC) {
                        System.out.print("C");
                        System.out.println();
                        flagA = true;
                        flagB = false;
                        flagC = false;
                        lock.notifyAll();
                        i++;
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }


}
