package com.xgsama.jvm.zzm.chapter2;

/**
 * JavaStackOOM
 * VM Args: -Xss2M
 *
 * @author xgSama
 * @date 2020/12/12 15:07
 */
public class JavaStackOOM {
    private void dontStop() {
        while (true) {}
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    dontStop();
                }
            });
        }
    }

    public static void main(String[] args) {
        JavaStackOOM oom = new JavaStackOOM();
        oom.stackLeakByThread();
    }
}
