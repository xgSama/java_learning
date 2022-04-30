package com.xgsama.java.basis.nio.reactor;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * EchoServerReactor
 *
 * @author: xgsama
 * @date: 2022-04-09 13:55:22
 */
@Slf4j
public class EchoServerReactor implements Runnable {

    Selector selector;
    ServerSocketChannel serverSocket;

    EchoServerReactor() throws IOException {
        // Reactor初始化
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();

        InetSocketAddress address =
                new InetSocketAddress("127.0.0.1", 8899);
        serverSocket.socket().bind(address);
        log.info("服务端已经开始监听：" + address);
        // 非阻塞
        serverSocket.configureBlocking(false);

        // 分步处理,第一步,接收accept事件
        SelectionKey sk =
                serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        // attach callback object, AcceptorHandler
        sk.attach(new AcceptorHandler());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set<SelectionKey> selected = selector.selectedKeys();
                Iterator<SelectionKey> it = selected.iterator();
                while (it.hasNext()) {
                    //Reactor负责dispatch收到的事件
                    SelectionKey sk = it.next();
                    dispatch(sk);
                }
                selected.clear();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void dispatch(SelectionKey sk) {
        Runnable handler = (Runnable) sk.attachment();
        //调用之前attach绑定到选择键的handler处理器对象
        if (handler != null) {
            handler.run();
        }
    }


    // Handler:新连接处理器
    class AcceptorHandler implements Runnable {
        public void run() {
            try {
                SocketChannel channel = serverSocket.accept();
                log.info("接收到一个连接");
                if (channel != null)
                    new EchoHandler(selector, channel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(new EchoServerReactor()).start();
    }

}
