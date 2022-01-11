package com.xgsama.netty.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

/**
 * EchoServer
 *
 * @author xgSama
 * @date 2021/3/31 11:04
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() throws InterruptedException {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        // 1、创建EventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            // 2、创建ServerBootstrap
            ServerBootstrap b = new ServerBootstrap();
            b
                    .group(group)
                    // 3、指定所使用的NIO传输Channel
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    // 4、使用指定的端口设置套接字地址
                    .localAddress(new InetSocketAddress(port))
                    // 5、添加一个EchoServerHandler到子Channel的ChannelPipeline
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // EchoServerHandler 被标注为@Shareable，所以我们可以总是使用同样的实例
                            socketChannel.pipeline()
                                    .addLast(new LoggingHandler(LogLevel.DEBUG))
                                    .addLast(new StringEncoder())
                                    .addLast(new StringDecoder())
                                    .addLast(serverHandler);
                        }
                    });

            // 6、异步地绑定服务器；调用sync()方法阻塞等待直到绑定完成
            ChannelFuture f = b.bind().sync();
            // 7、获取Channel 的CloseFuture，并且阻塞当前线程直到它完成
            f.channel().closeFuture().sync();

        } finally {
            // 8、关闭EventLoopGroup，释放所有的资源
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 7077;
        new EchoServer(port).start();
    }
}
