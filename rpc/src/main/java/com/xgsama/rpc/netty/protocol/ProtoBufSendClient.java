package com.xgsama.rpc.netty.protocol;

import com.xgsama.rpc.entity.MsgProto;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import lombok.extern.slf4j.Slf4j;

/**
 * ProtoBufSendClient
 *
 * @author : xgSama
 * @date : 2021/8/26 21:48:47
 */
@Slf4j
public class ProtoBufSendClient {

    static String content = "疯狂创客圈：高性能学习社群!";

    private final int serverPort;
    private final String serverIp;
    Bootstrap b = new Bootstrap();

    public ProtoBufSendClient(String ip, int port) {
        this.serverPort = port;
        this.serverIp = ip;
    }

    public void runClient() {
        //创建reactor 线程组
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();

        try {
            b.group(workerLoopGroup);
            b.channel(NioSocketChannel.class);
            b.remoteAddress(serverIp, serverPort);
            b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            b.handler(new ChannelInitializer<SocketChannel>() {
                //初始化客户端channel
                protected void initChannel(SocketChannel ch) throws Exception {
                    // 客户端channel流水线添加2个handler处理器
                    ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                    ch.pipeline().addLast(new ProtobufEncoder());
                }
            });

            ChannelFuture f = b.connect();
            f.addListener((ChannelFuture futureListener) ->
            {
                if (futureListener.isSuccess()) {
                    log.info("EchoClient客户端连接成功!");

                } else {
                    log.info("EchoClient客户端连接失败!");
                }

            });

            // 阻塞,直到连接完成
            f.sync();
            Channel channel = f.channel();

            //发送 Protobuf 对象
            for (int i = 0; i < 1000; i++) {
                MsgProto.Msg user = build(i, i + "->" + content);
                channel.writeAndFlush(user);
                log.info("发送报文数：" + i);
            }
            channel.flush();

            // 7 等待通道关闭的异步任务结束
            // 服务监听通道会一直等待通道关闭的异步任务结束
            ChannelFuture closeFuture = channel.closeFuture();
            closeFuture.sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 优雅关闭EventLoopGroup，
            // 释放掉所有资源包括创建的线程
            workerLoopGroup.shutdownGracefully();
        }

    }

    //构建ProtoBuf对象
    public MsgProto.Msg build(int id, String content) {
        MsgProto.Msg.Builder builder = MsgProto.Msg.newBuilder();
        builder.setId(id);
        builder.setContent(content);
        return builder.build();
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 9099;
        String ip = "localhost";
        new ProtoBufSendClient(ip, port).runClient();
    }


}
