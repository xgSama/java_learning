package com.xgsama.netty.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.Scanner;

/**
 * SendMsgClient
 *
 * @author : xgSama
 * @date : 2022/1/9 18:07:49
 */
public class SendMsgClient {
    public static void main(String[] args) {
        NioEventLoopGroup worker = new NioEventLoopGroup();

        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap
                    .group(worker)
                    .channel(NioSocketChannel.class)
                    .remoteAddress("127.0.0.1", 7077)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new StringEncoder())
                                    .addLast(new StringDecoder())
                                    .addLast(new LoggingHandler(LogLevel.DEBUG));
                        }
                    });

            ChannelFuture future = bootstrap.connect().sync();

            Channel channel = future.channel();

            for (int i = 0; i < 100; i++) {
                channel.writeAndFlush("窗前明月光，疑是地上霜。\n");
            }

            Scanner scanner = new Scanner(System.in);

            while (true) {

                String s = scanner.nextLine();
                if (s.equals("q")) break;
                channel.writeAndFlush(s + "\n");
                System.out.println("w: " + s);
            }

            channel.flush();

            channel.close();


        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }
    }
}
