package com.xgsama.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * HeartBeatHandler
 *
 * @author : xgSama
 * @date : 2021/11/5 14:02:32
 */
@Slf4j
public class HeartBeatHandler extends SimpleChannelInboundHandler<String> {
    int readIdleTimes = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        log.info(" ====== > [server] message received : " + msg);
        if ("I am alive".equals(msg)) {
            ctx.channel().writeAndFlush("got it!");
        } else {
            log.info(" 其他信息处理 ... " + msg);
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent event = (IdleStateEvent) evt;

        String eventType = null;
        switch (event.state()) {
            case READER_IDLE:
                eventType = "读空闲";
                readIdleTimes++; // 读空闲的计数加1
                break;
            case WRITER_IDLE:
                eventType = "写空闲";
                // 不处理
                break;
            case ALL_IDLE:
                eventType = "读写空闲";
                // 不处理
                break;
        }
        if (eventType.equals("读空闲")) {
            log.warn(ctx.channel().remoteAddress() + "超时事件：" + eventType);
        }
        if (readIdleTimes > 3) {
            log.error(" [server]读空闲超过3次，关闭连接");
            ctx.channel().writeAndFlush("you are out");
            ctx.channel().close();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("=== " + ctx.channel().remoteAddress() + " is active ===");
    }


}
