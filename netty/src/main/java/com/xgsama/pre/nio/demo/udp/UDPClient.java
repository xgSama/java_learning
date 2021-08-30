package com.xgsama.pre.nio.demo.udp;

import com.xgsama.common.crazy.util.DateUtil;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * UDPClient
 *
 * @author : xgSama
 * @date : 2021/8/15 16:54:24
 */
public class UDPClient {

    public void send() throws Exception {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        System.out.println("客户端启动成功。。。");
        System.out.println("请输入发送内容：");

        while (scanner.hasNext()) {
            String next = scanner.nextLine();
            buffer.put((DateUtil.getNow() + ">>" + next).getBytes(StandardCharsets.UTF_8));
            buffer.flip();

            datagramChannel.send(buffer, new InetSocketAddress("localhost", 18899));
            buffer.clear();
        }

        datagramChannel.close();
    }

    public static void main(String[] args) throws Exception {
        new UDPClient().send();
    }
}
