package com.xgsama.pre.nio;

import org.junit.Test;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;

/**
 * BufferDemo
 *
 * @author xgSama
 * @date 2021/1/24 15:14
 */
public class BufferDemo {

    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);

        intBuffer.put(1);
        intBuffer.put(2);
        intBuffer.put(3);
        intBuffer.put(4);
        intBuffer.put(5);
//        intBuffer.put(6);

        intBuffer.flip();

        while (true) {
            intBuffer.mark();
            while (intBuffer.hasRemaining()) {
                System.out.println(intBuffer.get());

            }

            intBuffer.reset();

        }


    }

    @Test
    public void test1() {
        String str = "hello";

        // 1、分配一个指定大小的缓存区
        System.out.println("----- allocate() -----");
        ByteBuffer buf = ByteBuffer.allocate(1024);
        print(buf);

        // 2、添加数据
        System.out.println("----- put() -----");
        buf.put(str.getBytes());
        print(buf);

        // 3、切换读取模式
        System.out.println("----- flip() -----");
        buf.flip();
        print(buf);

        // 4、读取数据
        System.out.println("----- get() -----");
        byte[] tar = new byte[buf.limit()];
        buf.get(tar);
        print(buf);
        System.out.println(new String(tar));

        // 5、rewind(): 可重复读数据
        System.out.println("----- rewind() -----");
        buf.rewind();
        print(buf);

        // 6、清空缓存区:缓冲区中数据存在，处于被遗忘状态
        System.out.println("----- clear() -----");
        buf.clear();
        print(buf);

        System.out.println((char) buf.get());
        print(buf);

    }

    @Test
    public void test2() {
        String str = "hello";
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put(str.getBytes());

        System.out.println("----- get(0,2) -----");
        buf.flip();
        byte[] tar = new byte[buf.limit()];
        buf.get(tar, 0, 2);
        print(buf);

        System.out.println("----- mark() -----");
        buf.mark();

        System.out.println("----- get(2,2) -----");
        buf.get(tar, 2, 2);
        print(buf);

        System.out.println("----- reset() -----");
        buf.reset();
        print(buf);

        if (buf.hasRemaining()) {
            System.out.println(buf.remaining());
        }

    }


    @SuppressWarnings("StringBufferReplaceableByString")
    private void print(Buffer buf) {

        StringBuilder sb = new StringBuilder();
        sb
//                .append("mark:").append(buf.mark()).append(", ")
                .append("position:").append(buf.position()).append(", ")
                .append("limit:").append(buf.limit()).append(", ")
                .append("capacity:").append(buf.capacity());

        System.out.println(sb.toString());

    }
}
