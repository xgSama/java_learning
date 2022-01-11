package com.xgsama.netty.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;

import java.nio.charset.StandardCharsets;

/**
 * EchoServerHandler
 *
 * @author xgSama
 * @date 2021/3/31 10:59
 */
@ChannelHandler.Sharable // 标示一个ChannelHandler可以被多个Channel安全地共享
public class EchoServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
        ctx.writeAndFlush(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 将未决消息冲刷到远程节点，并且关闭该Channel
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 打印异常栈跟踪
        cause.printStackTrace();
        // 关闭该channel
        ctx.close();
    }
}
