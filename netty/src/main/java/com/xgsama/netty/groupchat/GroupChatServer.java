package com.xgsama.netty.groupchat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * GroupChatServer
 *
 * @author xgSama
 * @date 2021/6/19 16:08
 */
public class GroupChatServer {
    private int port;

    public GroupChatServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        final GroupChatServerHandler serverHandler = new GroupChatServerHandler();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 获取pipeline
                            ChannelPipeline pipeline = ch.pipeline();
                            // 向pipeline中添加一个解码器
                            pipeline.addLast("decoder", new StringDecoder());
                            // 向pipeline中添加一个边码器
                            pipeline.addLast("encoder", new StringEncoder());
                            // 加入自己的业务处理Handler
                            pipeline.addLast(serverHandler);
                        }
                    });

            System.out.println("server started");
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

            // 监听关闭时间
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception {
        GroupChatServer server = new GroupChatServer(7000);
        server.run();
    }
}
