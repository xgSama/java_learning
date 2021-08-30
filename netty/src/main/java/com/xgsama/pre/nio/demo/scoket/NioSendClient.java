package com.xgsama.pre.nio.demo.scoket;

import java.io.File;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * NioSendClient
 *
 * @author : xgSama
 * @date : 2021/8/15 11:52:28
 */
public class NioSendClient {
    private static final Charset utf8 = StandardCharsets.UTF_8;

    public static void main(String[] args) {
        NioSendClient client = new NioSendClient(); // 启动客户端连接
        client.sendFile(); // 传输文件

    }

    public void sendFile() {
        String sourceFile = "input/pom.xml";
        String destFile = "pom.dest.xml";

        final File file = new File(sourceFile);
        if (!file.exists()) {
            return;
        }

        try (final FileChannel fileChannel = new FileInputStream(file).getChannel();
             final SocketChannel socketChannel = SocketChannel.open()) {
            socketChannel.socket().connect(new InetSocketAddress("localhost", 9999));
            System.out.println("成功连接服务端");
            while (!socketChannel.finishConnect()) {
            }
            ByteBuffer buffer = sendFileNameAndLength(destFile, file, socketChannel);

            int length = sendContent(file, fileChannel, socketChannel, buffer);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ByteBuffer sendFileNameAndLength(String destFile, File file, SocketChannel socketChannel) throws Exception {
        final ByteBuffer fileNameByteBuffer = utf8.encode(destFile);
        final ByteBuffer buffer = ByteBuffer.allocate(1024);
        final int fileNameLength = fileNameByteBuffer.limit();
        buffer.putInt(fileNameLength);
        buffer.flip();
        socketChannel.write(buffer);
        buffer.clear();
        System.out.println("文件名称长度发送成功：" + fileNameLength);

        socketChannel.write(fileNameByteBuffer);
        System.out.println("文件名称发送成功：" + destFile);

        // 发送文件长度
        buffer.putLong(file.length());
        buffer.flip();
        socketChannel.write(buffer);
        buffer.clear();

        System.out.println("文件长度发送成功" + file.length());

        return buffer;
    }

    // 发送文件内容
    public int sendContent(File file, FileChannel fileChannel,
                           SocketChannel socketChannel, ByteBuffer buffer) throws Exception {
        System.out.println("开始文件传输");
        int length = 0;
        long progress = 0;
        while ((length = fileChannel.read(buffer)) > 0) {
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();

            progress += length;

            System.out.println("| " + 100 * progress / file.length() + "% |");
        }

        return length;

    }
}
