package com.xgsama.netty.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * EchoClient
 *
 * @author xgSama
 * @date 2021/3/31 11:18
 */
public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        // 创建反应器轮询
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 客户端创建Bootstrap
            Bootstrap b = new Bootstrap();
            // Bootstrap绑定EventLoopGroup
            b.group(group)
                    // 指定所使用的NIO传输Channel
                    .channel(NioSocketChannel.class)
                    // 绑定服务端地址
                    .remoteAddress(new InetSocketAddress(host, port))
                    // 用于处理请求的ChannelHandler
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            // 异步地绑定服务器；调用sync()方法阻塞等待直到绑定完成
            ChannelFuture f = b.connect().sync();
            // 获取Channel 的CloseFuture，并且阻塞当前线程直到它完成
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {

        String host = "localhost";
        new EchoClient(host, 7077).start();

    }
}
