package com.xgsama.jvm.reference;

import java.io.IOException;

/**
 * NormalReference
 *
 * @author xgSama
 * @date 2021/1/23 15:11
 */
public class NormalReference {

    public static void main(String[] args) throws IOException {
        RefTest r = new RefTest();
        r = null;
        System.gc();

        System.in.read();
    }
}
