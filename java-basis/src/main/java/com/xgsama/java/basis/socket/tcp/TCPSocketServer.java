package com.xgsama.java.basis.socket.tcp;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCPSocketServer
 *
 * @author : xgSama
 * @date : 2021/8/16 20:27:31
 */
public class TCPSocketServer {
    public static void main(String[] args) {
        try {
            // 创建服务端socket
            ServerSocket serverSocket = new ServerSocket(8088);

            // 创建客户端socket
            Socket socket;

            //循环监听等待客户端的连接
            while (true) {
                // 监听客户端
                socket = serverSocket.accept();

                ServerThread thread = new ServerThread(socket);
                thread.start();

                InetAddress address = socket.getInetAddress();
                System.out.println("当前客户端的IP：" + address.getHostAddress());
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}