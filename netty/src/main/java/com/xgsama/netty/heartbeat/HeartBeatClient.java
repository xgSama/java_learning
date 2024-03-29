package com.xgsama.netty.heartbeat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * HeartBeatClient
 *
 * @author : xgSama
 * @date : 2021/11/5 14:05:23
 */
@Slf4j
public class HeartBeatClient {

    int port;
    Channel channel;
    Random random;

    public HeartBeatClient(int port) {
        this.port = port;
        random = new Random();
    }

    public static void main(String[] args) throws Exception {
        HeartBeatClient client = new HeartBeatClient(8090);
        client.start();
    }

    public void start() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new HeartBeatClientInitializer());

            connect(bootstrap, port);
            String text = "I am alive";
            while (channel.isActive()) {
                sendMsg(text);
            }
        } catch (Exception e) {
            // do something
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public void connect(Bootstrap bootstrap, int port) throws Exception {
        channel = bootstrap.connect("localhost", 8090).sync().channel();
    }

    public void sendMsg(String text) throws Exception {
        int num = random.nextInt(3);
        Thread.sleep(num * 1000);
        channel.writeAndFlush(text);
    }

    static class HeartBeatClientInitializer extends ChannelInitializer<Channel> {

        @Override
        protected void initChannel(Channel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();
            pipeline.addLast("decoder", new StringDecoder());
            pipeline.addLast("encoder", new StringEncoder());
            pipeline.addLast(new HeartBeatClientHandler());
        }
    }

    static class HeartBeatClientHandler extends SimpleChannelInboundHandler<String> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
            log.info(" client received :" + msg);
            if (msg != null && msg.equals("you are out")) {
                log.info(" server closed connection , so client will close too");
                ctx.channel().closeFuture();
            }
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            ctx.channel().close();
        }
    }
}
