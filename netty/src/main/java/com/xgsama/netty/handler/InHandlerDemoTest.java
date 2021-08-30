package com.xgsama.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;

/**
 * InHandlerDemoTest
 *
 * @author : xgSama
 * @date : 2021/8/17 19:57:14
 */
public class InHandlerDemoTest {

    public static void main(String[] args) {
        InHandlerDemo inHandler = new InHandlerDemo();

        ChannelInitializer<EmbeddedChannel> i = new ChannelInitializer<>() {

            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(inHandler);
            }
        };

        EmbeddedChannel channel = new EmbeddedChannel(i);
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeInt(1);
        channel.writeInbound(buffer);
        channel.finish();

        channel.close();

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
