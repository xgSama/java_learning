package com.xgsama.jvm.reference;

/**
 * RefTest
 *
 * @author xgSama
 * @date 2021/1/23 15:51
 */
public class RefTest {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize()");
    }
}
