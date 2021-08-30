package com.xgsama.java.basis.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * UDPSocketServer
 *
 * @author : xgSama
 * @date : 2021/8/16 21:21:39
 */
public class UDPSocketServer {

    public static void main(String[] args) {
        try {
            // 要接收的报文
            byte[] bytes = new byte[1024];
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length);

            // 创建socket并指定端口
            DatagramSocket socket = new DatagramSocket(8088);

            // 接收socket客户端发送的数据。如果未收到会一致阻塞
            socket.receive(packet);
            String receiveMsg = new String(packet.getData(), 0, packet.getLength());
            System.out.println(packet.getLength());
            System.out.println(receiveMsg);

            // 关闭socket
            socket.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
