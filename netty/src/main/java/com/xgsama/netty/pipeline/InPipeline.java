package com.xgsama.netty.pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * InPipeline
 *
 * @author : xgSama
 * @date : 2021/8/17 20:08:06
 */
@Slf4j
public class InPipeline {

    static class SimpleInHandlerA extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.info("入站处理器 -- A -- 被回调");
            super.channelRead(ctx, msg);
        }
    }

    static class SimpleInHandlerB extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.info("入站处理器 -- B -- 被回调");
            super.channelRead(ctx, msg);
        }
    }

    static class SimpleInHandlerC extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.info("入站处理器 -- C -- 被回调");
            super.channelRead(ctx, msg);
        }
    }

    public static void main(String[] args) {
        ChannelInitializer<EmbeddedChannel> i = new ChannelInitializer<EmbeddedChannel>() {

            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new SimpleInHandlerA());
                ch.pipeline().addLast(new SimpleInHandlerB());
                ch.pipeline().addLast(new SimpleInHandlerC());
            }
        };

        EmbeddedChannel channel = new EmbeddedChannel(i);
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeInt(1);
        channel.writeInbound(buffer);

    }
}
