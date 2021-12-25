package com.xgsama.rpc.netty.protocol;

import com.xgsama.rpc.entity.MsgProto;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * ProtoBufServer
 *
 * @author : xgSama
 * @date : 2021/8/26 21:17:05
 */
@Slf4j
public class ProtoBufServer {

    private final int serverPort;
    ServerBootstrap b = new ServerBootstrap();

    public ProtoBufServer(int port) {
        this.serverPort = port;
    }

    public void runServer() {
        // 创建反应器线程组
        NioEventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerLoopGroup = new NioEventLoopGroup();

        try {
            b.group(bossLoopGroup, workerLoopGroup);
            b.channel(NioServerSocketChannel.class);
            b.localAddress(serverPort);
            //4 设置通道的参数
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            b.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            b.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline()
                            .addLast(new ProtobufVarint32FrameDecoder())
                            .addLast(new ProtobufDecoder(MsgProto.Msg.getDefaultInstance()))
                            .addLast(new ProtobufBusinessDecoder());
                }
            });

            // 6 开始绑定server
            // 通过调用sync同步方法阻塞直到绑定成功
            ChannelFuture channelFuture = b.bind().sync();
            log.info(" 服务器启动成功，监听端口: " +
                    channelFuture.channel().localAddress());

            // 7 等待通道关闭的异步任务结束
            // 服务监听通道会一直等待通道关闭的异步任务结束
            ChannelFuture closeFuture = channelFuture.channel().closeFuture();
            closeFuture.sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 8 优雅关闭EventLoopGroup，
            // 释放掉所有资源包括创建的线程
            workerLoopGroup.shutdownGracefully();
            bossLoopGroup.shutdownGracefully();
        }

    }

    static class ProtobufBusinessDecoder extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            MsgProto.Msg protoMsg = ((MsgProto.Msg) msg);
            log.info("收到一个Protobuf POJO =>> ");
            log.info("protoMsg.getId() = " + protoMsg.getId());
            log.info("protoMsg.getContent() = " + protoMsg.getContent());
        }
    }


    public static void main(String[] args) {
        int port = 9099;

        new ProtoBufServer(port).runServer();
    }
}
