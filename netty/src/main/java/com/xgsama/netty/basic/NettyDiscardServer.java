package com.xgsama.netty.basic;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * NettyDiscardServer
 *
 * @author : xgSama
 * @date : 2021/8/14 18:25:26
 */
public class NettyDiscardServer {

    private final int serverPort;

    ServerBootstrap serverBootstrap = new ServerBootstrap();


    public NettyDiscardServer(int serverPort) {
        this.serverPort = serverPort;
    }

    public void runServer() {
        // 创建反应器轮询
        NioEventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerLoopGroup = new NioEventLoopGroup();

        try {
            // 1. 设置反应器轮询组
            serverBootstrap.group(bossLoopGroup, workerLoopGroup);
            // 2. 设置nio类型的通道
            serverBootstrap.channel(NioServerSocketChannel.class);
            // 3. 设置监听端口
            serverBootstrap.localAddress(serverPort);
            // 4. 设置通道的参数
            serverBootstrap.childOption(ChannelOption.TCP_NODELAY, true);
            // 5. 装配子通道轮水线
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                // 有连接到达时会创建一个通道
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new NettyDiscardHandler());
                }
            });
            // 6. 开始绑定服务器
            // 通过调用sync同步方法阻塞直到绑定成功
            ChannelFuture channelFuture = serverBootstrap.bind().sync();

            ChannelFuture closeFuture = channelFuture.channel().closeFuture();
            closeFuture.sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 8. 优雅的关闭EventLoopGroup
            // 释放掉所有资源，包括创建的线程
            workerLoopGroup.shutdownGracefully();
            bossLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = 9998;

        new NettyDiscardServer(port).runServer();

    }
}
