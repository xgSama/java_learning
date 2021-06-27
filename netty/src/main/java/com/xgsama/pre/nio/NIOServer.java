package com.xgsama.pre.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NIOServer
 *
 * @author xgSama
 * @date 2021/4/29 16:35
 */
public class NIOServer {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel server = ServerSocketChannel.open();

        Selector selector = Selector.open();

        server.socket().bind(new InetSocketAddress(6666));
        // 设置为非阻塞
        server.configureBlocking(false);

        // 把ServerSocketChannel注册到Selector，关心事件为OP_ACCEPT
        server.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            if (selector.select(1000) == 0) {
                System.out.println("服务器等待了一秒，无连接");
                continue;
            } else {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    // 根据key 对应的通道发生的事件做相应的处理

                    if (key.isAcceptable()) {
                        SocketChannel socketChannel = server.accept();
                        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    }

                    if (key.isReadable()) {

                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        channel.read(buffer);

                        System.out.println("from client: " + new String(buffer.array()));
                    }
                }
            }
        }
    }
}
