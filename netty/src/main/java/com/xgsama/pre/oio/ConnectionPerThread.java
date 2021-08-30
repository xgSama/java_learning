package com.xgsama.pre.oio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * ConnectionPerThread
 *
 * @author : xgSama
 * @date : 2021/8/16 19:48:15
 */
public class ConnectionPerThread implements Runnable {

    public static void main(String[] args) {
        new Thread(new ConnectionPerThread()).start();
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(9978);
            while (!Thread.interrupted()) {
                Socket socket = serverSocket.accept();
                InetAddress inetAddress = socket.getInetAddress();
                System.out.println(Arrays.toString(inetAddress.getAddress()));
                // 接收一个连接后，为socket连接，新建一个专属的处理器对象
                Handler handler = new Handler(socket);
                // 创建新线程，专门负责一个连接的处理
                new Thread(handler).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 处理器，这里将内容会显到客户端
    static class Handler implements Runnable {
        final Socket socket;


        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter writer = new PrintWriter(socket.getOutputStream());
            ) {
                // 读取数据
                StringBuilder sb = new StringBuilder().append("已经收到：");
                String msg = null;
                while ((msg = reader.readLine()) != null) {
                    sb.append(msg);
                }
                sb.append("\n");

                socket.shutdownInput();
                // 处理业务逻辑，获取处理结果
                // 写入结果
                writer.write(sb.toString());
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {

                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
