package com.xgsama.pre.nio;

import com.xgsama.util.CommonUtil;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;

/**
 * ChannelDemo
 *
 * @author xgSama
 * @date 2021/2/2 10:16
 */
public class ChannelDemo {

    // TODO 利用通道完成文件的复制(非直接缓冲区)
    @Test
    public void test1() throws Exception {

        long start = Instant.now().toEpochMilli();

        FileInputStream fis = new FileInputStream("G:\\迅雷下载\\刀剑：序列之争.mp4");
        FileOutputStream fos = new FileOutputStream("G:\\迅雷下载\\刀剑：序列之争1.mp4");

        // 获取通道
        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();

        // 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 将通道中的数据存入缓冲区
        while ((fisChannel.read(buf)) != -1) {

            try {
                buf.flip();
                fosChannel.write(buf);
                buf.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        CommonUtil.release(fosChannel, fisChannel, fos, fis);

        System.out.println(Instant.now().toEpochMilli() - start);

    }

    // TODO 利用通道完成文件的复制(内存映射文件)
    @Test
    public void test2() throws Exception {

        long start = Instant.now().toEpochMilli();

        FileChannel inChannel = FileChannel.open(Paths.get("G:\\迅雷下载\\刀剑：序列之争.mp4"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("G:\\迅雷下载\\刀剑：序列之争2.mp4"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        // 内存映射文件（只有ByteBuffer支持）
        MappedByteBuffer inMappedBuffer = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuffer = outChannel.map(MapMode.READ_WRITE, 0, inChannel.size());

        // 直接对缓冲区进行数据的读写操作
        byte[] dst = new byte[inMappedBuffer.limit()];
        inMappedBuffer.get(dst);
        outMappedBuffer.put(dst);


        CommonUtil.release(outChannel, inChannel);


        System.out.println(Instant.now().toEpochMilli() - start);
    }
}
