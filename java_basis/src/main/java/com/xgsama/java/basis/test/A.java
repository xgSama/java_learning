package com.xgsama.java.basis.test;

import java.util.Date;

/**
 * A
 *
 * @author xgSama
 * @date 2020/12/11 15:54
 */
public class A {
    public static final String string = "s";

    public static void main(String[] args) {
        System.out.println(A.string);
        System.out.println(System.currentTimeMillis());

        System.out.println(new Date(1293840000));
        System.out.println(new Date(1607677130));
    }
}
