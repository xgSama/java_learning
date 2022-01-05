package com.xgsama.java.basis.nio;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * ZeroCopy
 *
 * @author : xgSama
 * @date : 2022/1/4 20:52:31
 */
public class ZeroCopy {
    public static void main(String[] args) throws Exception {
        String src = "/Users/xgSama/Downloads/draw.io-for-intel_15.8.4.dmg";
        String dest1 = "/Users/xgSama/Downloads/draw.io-for-intel_15.8.4.dmg2";
        String dest2 = "/Users/xgSama/Downloads/draw.io-for-intel_15.8.4.dmg3";
        normalCopy(src, dest1);
        zeroCopy(src, dest2);
    }

    public static void normalCopy(String src, String dest) throws Exception {

        long startTime = System.currentTimeMillis();

        FileInputStream fileInputStream = new FileInputStream(src);
        FileOutputStream fileOutputStream = new FileOutputStream(dest);

        byte[] buffer = new byte[4096];
        while ((fileInputStream.read(buffer)) >= 0) {
            fileOutputStream.write(buffer);
        }
        fileOutputStream.flush();

        long endTime = System.currentTimeMillis();
        System.out.println("复制用时间" + (endTime - startTime) + "ms");
    }

    public static void zeroCopy(String src, String dest) throws Exception {
        long startTime = System.currentTimeMillis();

        File srcFile = new File(src);
        File destFile = new File(dest);

        FileChannel srcChannel = new RandomAccessFile(srcFile, "r").getChannel();
        FileChannel destChannel = new RandomAccessFile(destFile, "rw").getChannel();

        srcChannel.transferTo(0, srcFile.length(), destChannel);

        long endTime = System.currentTimeMillis();
        System.out.println("复制用时间" + (endTime - startTime) + "ms");
    }
}
