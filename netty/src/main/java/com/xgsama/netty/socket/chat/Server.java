package com.xgsama.netty.socket.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Server
 *
 * @author xgSama
 * @date 2021/1/22 14:43
 */
public class Server {
    private static CopyOnWriteArrayList<Channel> all = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws Exception {
        System.out.println("-------- Server --------");

        ServerSocket server = new ServerSocket(2233);

        // TODO 阻塞式连接等待accept
        while (true) {
            Socket clent = server.accept();
            System.out.println("一个客户建立了连接。。。");
            Channel channel = new Channel(clent);
            all.add(channel);

            new Thread(channel).start();
        }

    }

    static class Channel implements Runnable {
        private DataInputStream dis;
        private DataOutputStream dos;
        private final Socket client;
        private boolean isRunning;
        private String name;

        public Channel(Socket socket) {
            client = socket;

            try {
                dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
                isRunning = true;
                this.name = receive();
                this.send("Welcome " + this.name);
                this.sendSys(this.name);
            } catch (Exception e) {
                release();
            }
        }

        // 接收消息
        private String receive() {

            String msg = "";

            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                System.out.println("receive");
                release();
            }
            return msg;
        }

        // 发送消息
        private void send(String msg) {
            try {
                dos.writeUTF(msg);
            } catch (Exception e) {
                System.out.println("send");
                release();
            }
        }

        private void sendAll(String msg) {
            boolean isPrivate = msg.startsWith("@");

            if (isPrivate) {
                // 获取目标和数据
                String target = msg.substring(1, msg.indexOf(":"));
                msg = msg.substring(msg.indexOf(":") + 1);

                for (Channel channel : all) {
                    if (channel.name.equals(target)) {
                        channel.send(this.name + "悄悄对你说:>" + msg);
                    }
                }
            } else {
                for (Channel channel : all) {
                    if (channel == this)
                        continue;
                    channel.send(this.name + ":> " + msg);
                }
            }
        }

        private void sendSys(String msg) {
            for (Channel channel : all) {
                if (channel == this)
                    continue;
                channel.send("============ 欢迎" + this.name + "进入房间 ============");
            }
        }


        // 释放资源
        private void release() {
            this.isRunning = false;
            ChatUtils.close(dis, dos, client);
        }

        @Override
        public void run() {
            while (isRunning) {
                String msg = receive();
                if (!msg.equals("") && !msg.equals("Q")) {
//                    send(msg);
                    sendAll(msg);
                } else {
                    isRunning = false;
                }
            }
        }
    }
}
