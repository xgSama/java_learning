package com.xgsama.java.basis.io;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * RandomTest
 *
 * @author xgSama
 * @date 2020/11/10 20:17
 */
public class RandomTest {
    public static void main(String[] args) throws Exception {

        test3();
    }

    public static void test3() throws Exception {
        File src = new File("input/pom.xml");
        long len = src.length();
        int blocksize = 100;
        int size = (int) Math.ceil(len * 1.0 / blocksize);

        int beginPos = 0;
        int actualSize = (int) (blocksize > len ? len : blocksize);

        for (int i = 0; i < size; i++) {
            beginPos = i * blocksize;
            if (i == size - 1) {
                actualSize = (int) len;
            } else {
                actualSize = blocksize;
                len -= actualSize;
            }

            test2(beginPos, blocksize);

            System.out.println();
            System.out.println(i +"----" +beginPos + "-----" + actualSize);
        }

        System.out.println(size);

    }


    public static void test2(int begin, int actualSize) throws Exception {
        RandomAccessFile raf = new RandomAccessFile(new File("java_basis/pom.xml"), "r");


        // 随机读取
        raf.seek(begin);
        byte[] flush = new byte[50];
        int len = -1;

        while ((len = raf.read(flush)) != -1) {
            if (actualSize > len) {
                System.out.print(new String(flush, 0, len));
                actualSize -= len;
            } else {
                System.out.print(new String(flush, 0, actualSize));
                break;
            }
        }

        raf.close();

    }


    public static void test1() throws Exception {
        RandomAccessFile raf = new RandomAccessFile(new File("java_basis/pom.xml"), "r");

        // 随机读取
        raf.seek(0);

        byte[] flush = new byte[1024];
        int len = -1;

        while ((len = raf.read(flush)) != -1) {
            System.out.println(new String(flush, 0, len));
        }

        raf.close();
    }
}
