package com.xgsama.java.basis.juc;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;

/**
 * Test
 *
 * @author 霜风折叶
 * @version 1.0
 * @date 2020/10/11 15:26
 */
public class Test extends AbstractQueuedSynchronizer {

    public static void main(String[] args) {

        System.out.println(1 << 27 - 1);

        System.out.println(Integer.toBinaryString(1 << 27 - 1));
        String s = Integer.toBinaryString(-1 << 27);
        System.out.println(s);
        System.out.println(s.length());
    }
}
