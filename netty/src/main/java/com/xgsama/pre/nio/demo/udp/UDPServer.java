package com.xgsama.pre.nio.demo.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * UDPServer
 *
 * @author : xgSama
 * @date : 2021/8/15 17:01:56
 */
public class UDPServer {

    public void receive() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);
        datagramChannel.bind(new InetSocketAddress("localhost", 18899));
        System.out.println("UDP服务器启动成功");
        Selector selector = Selector.open();

        datagramChannel.register(selector, SelectionKey.OP_READ);
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            ByteBuffer buffer = ByteBuffer.allocate(10240);

            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isReadable()) {
                    datagramChannel.receive(buffer);
                    buffer.flip();

                    System.out.println(new String(buffer.array(), 0, buffer.limit()));

                    buffer.clear();
                }

            }

            iterator.remove();

        }
        selector.close();
        datagramChannel.close();
    }

    public static void main(String[] args) throws IOException {
        new UDPServer().receive();
    }
}
