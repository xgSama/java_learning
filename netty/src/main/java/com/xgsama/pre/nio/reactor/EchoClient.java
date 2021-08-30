package com.xgsama.pre.nio.reactor;

import com.xgsama.common.crazy.util.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * EchoClient
 *
 * @author : xgSama
 * @date : 2021/8/16 21:38:29
 */
@Slf4j
public class EchoClient {


    public void start() throws IOException {

        InetSocketAddress address = new InetSocketAddress("localhost", 18899);

        // 1、获取通道（channel）
        SocketChannel socketChannel = SocketChannel.open(address);
        log.info("客户端连接成功");
        // 2、切换成非阻塞模式
        socketChannel.configureBlocking(false);
        //不断的自旋、等待连接完成，或者做一些其他的事情
        while (!socketChannel.finishConnect()) {

        }
        log.info("客户端启动成功！");

        //启动接受线程
        Processor processor = new Processor(socketChannel);
        new Thread(processor).start();

    }

    static class Processor implements Runnable {
        final Selector selector;
        final SocketChannel channel;

        Processor(SocketChannel channel) throws IOException {
            //Reactor初始化
            selector = Selector.open();
            this.channel = channel;
            channel.register(selector,
                    SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }

        public void run() {
            try {
                while (!Thread.interrupted()) {
                    selector.select();
                    Set<SelectionKey> selected = selector.selectedKeys();
                    Iterator<SelectionKey> it = selected.iterator();
                    while (it.hasNext()) {
                        SelectionKey sk = it.next();
                        if (sk.isWritable()) {
                            ByteBuffer buffer = ByteBuffer.allocate(1024);

                            Scanner scanner = new Scanner(System.in);
                            log.info("请输入发送内容:");
                            if (scanner.hasNext()) {
                                SocketChannel socketChannel = (SocketChannel) sk.channel();
                                String next = scanner.next();
                                buffer.put((DateUtil.getNow() + " >>" + next).getBytes());
                                buffer.flip();
                                // 操作三：发送数据
                                socketChannel.write(buffer);
                                buffer.clear();
                            }

                        }
                        if (sk.isReadable()) {
                            // 若选择键的IO事件是“可读”事件,读取数据
                            SocketChannel socketChannel = (SocketChannel) sk.channel();

                            //读取数据
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            int length = 0;
                            while ((length = socketChannel.read(byteBuffer)) > 0) {
                                byteBuffer.flip();
                                log.info("server echo:" + new String(byteBuffer.array(), 0, length));
                                byteBuffer.clear();
                            }

                        }
                        //处理结束了, 这里不能关闭select key，需要重复使用
                        //selectionKey.cancel();
                    }
                    selected.clear();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new EchoClient().start();
    }
}
