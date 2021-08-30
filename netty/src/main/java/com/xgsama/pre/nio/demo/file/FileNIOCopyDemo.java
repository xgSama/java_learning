package com.xgsama.pre.nio.demo.file;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * FileNIOCopyDemo
 *
 * @author : xgSama
 * @date : 2021/8/14 21:29:53
 */
public class FileNIOCopyDemo {

    public static void main(String[] args) throws IOException {
        nioCopyResourceFile();

        final File file = new File("input/server.png");
        System.out.println(file.length());
    }

    public static void nioCopyResourceFile() {

        String sourcePath = "input/server.png";

        String destDecodePath = "input/server.nio.png";

        System.out.println(sourcePath + " ---> " + destDecodePath);
        System.out.println("destDecodePath" + destDecodePath);
        try {
            CopyFileByNIO(sourcePath, destDecodePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void CopyFileByNIO(String srcPath, String destPath) throws Exception {
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);

        try (FileInputStream fis = new FileInputStream(srcFile);
             FileOutputStream fos = new FileOutputStream(destFile);
             FileChannel inChannel = fis.getChannel();
             FileChannel outChannel = fos.getChannel()) {

            final ByteBuffer buf = ByteBuffer.allocate(1024);
            while (inChannel.read(buf) != -1) {
                buf.flip();
                int outLength = 0;
                while ((outLength = outChannel.write(buf)) != 0) {
                    System.out.println("写入的字节数：" + outLength);
                }
                buf.clear();
            }

            outChannel.force(true);
        }
    }
}