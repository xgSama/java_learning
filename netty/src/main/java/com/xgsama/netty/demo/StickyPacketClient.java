package com.xgsama.netty.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * StickyPacketClient
 *
 * @author : xgSama
 * @date : 2022/1/8 17:28:17
 */
@Slf4j
public class StickyPacketClient {

    public static void main(String[] args) {
        NioEventLoopGroup worker = new NioEventLoopGroup();

        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(worker);
            bootstrap.remoteAddress("127.0.0.1", 8099);

            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            for (int i = 0; i < 100; i++) {
                                ByteBuf buf = ctx.alloc().buffer();
                                buf.writeBytes(new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17});
                                ctx.writeAndFlush(buf);
                            }
                        }
                    });
                }
            });

            System.out.println("启动----");
            ChannelFuture channelFuture = bootstrap.connect().sync();
            channelFuture.channel().closeFuture().sync();

        } catch (Exception e) {
            log.error("client error!", e);
        } finally {
            worker.shutdownGracefully();
        }
    }
}
