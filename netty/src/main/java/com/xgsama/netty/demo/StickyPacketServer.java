package com.xgsama.netty.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * StickyPacketServer
 *
 * @author : xgSama
 * @date : 2022/1/8 17:20:07
 */
@Slf4j
public class StickyPacketServer {


    public static void main(String[] args) {
        StickyPacketServer server = new StickyPacketServer();

        server.start();
    }


    void start() {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup(1);

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.option(ChannelOption.SO_RCVBUF, 10);
            // bootstrap.option(ChannelOption.RCVBUF_ALLOCATOR,new FixedRecvByteBufAllocator(10));


            bootstrap.group(boss, worker);
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                }
            });


            ChannelFuture channelFuture = bootstrap.bind(8099).sync();
            channelFuture.channel().closeFuture().sync();


        } catch (Exception e) {
            log.error("server error！", e);
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
