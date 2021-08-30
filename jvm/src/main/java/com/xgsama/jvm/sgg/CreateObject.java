package com.xgsama.jvm.sgg;

import org.openjdk.jol.info.ClassLayout;

/**
 * CreateObject
 *
 * @author : xgSama
 * @date : 2021/8/11 20:45:16
 */
public class CreateObject {
    public static void main(String[] args) {
        Object obj = new Object();

        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }
}
