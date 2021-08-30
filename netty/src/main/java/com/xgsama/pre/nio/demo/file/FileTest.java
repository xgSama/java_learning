package com.xgsama.pre.nio.demo.file;

import java.io.File;

/**
 * FileTest
 *
 * @author : xgSama
 * @date : 2021/8/15 17:42:52
 */
public class FileTest {
    public static void main(String[] args) {
        File file = new File("F:\\JAVA\\java_learning\\input\\url.png");
        System.out.println(file.exists());
        System.out.println(file.getAbsoluteFile());
        if (file.exists()) {
            file.delete();
        }
    }
}
