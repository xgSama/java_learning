package com.xgsama.jvm.chapter2;

/**
 * JavaVMStackSOF
 * VM Args: -Xss128k
 *
 * @author xgSama
 * @date 2020/12/12 14:59
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF oom = new JavaVMStackSOF();

        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}
