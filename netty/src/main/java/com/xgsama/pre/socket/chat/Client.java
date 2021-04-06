package com.xgsama.pre.socket.chat;

import java.io.*;
import java.net.Socket;

/**
 * Client
 *
 * @author xgSama
 * @date 2021/1/22 14:42
 */
public class Client {

    public void client2() throws Exception {
        System.out.println("---------- Client ----------");
        System.out.print("Please input your nickname: ");
        BufferedReader name = new BufferedReader(new InputStreamReader(System.in));
        Socket client = new Socket("localhost", 2233);
        new Thread(new Send(client, name.readLine())).start();
        new Thread(new Receive(client)).start();
    }

    public void client() throws Exception {
        Socket client = new Socket("localhost", 2233);

        System.out.println("输入：");
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        DataInputStream dis = new DataInputStream(client.getInputStream());

        boolean isRunning = true;
        while (isRunning) {
            String msg = console.readLine();

            dos.writeUTF(msg);
            dos.flush();

            // TODO 获取消息
            msg = dis.readUTF();
            System.out.println(msg);
        }


        dos.close();
        dis.close();
        client.close();

    }

    static class Send implements Runnable {

        private final BufferedReader console;
        private final Socket client;
        private boolean isRunning;
        private DataOutputStream dos;
        private String name;

        public Send(Socket client, String name) {
            this.client = client;
            this.name = name;
            console = new BufferedReader(new InputStreamReader(System.in));
            try {
                dos = new DataOutputStream(client.getOutputStream());
                isRunning = true;
                send(name);
            } catch (Exception e) {
                System.out.println("Send");
                release();
            }
        }

        private String getStrFromConsole() {
            try {
                return console.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "";
        }

        @Override
        public void run() {
            while (isRunning) {
                String msg = getStrFromConsole();
                if (!msg.equals("")) {
                    send(msg);
                }
            }

        }

        private void send(String msg) {
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (Exception e) {
                System.out.println("Send.send");
                release();
            }
        }

        private void release() {
            this.isRunning = false;
            ChatUtils.close(dos, client);
        }
    }

    static class Receive implements Runnable {

        private DataInputStream dis;
        private final Socket client;
        private boolean isRunning;

        public Receive(Socket client) {
            this.client = client;

            try {
                dis = new DataInputStream(client.getInputStream());
                isRunning = true;
            } catch (Exception e) {
                System.out.println("Receive");
                release();
            }

        }

        private String receive() {
            String msg = "";
            try {
                msg = dis.readUTF();
            } catch (Exception e) {
                System.out.println("Receive.receive");
                release();
            }

            return msg;
        }

        @Override
        public void run() {
            while (isRunning) {
                String msg = receive();
                if (!msg.equals("")) {
                    System.out.println(msg);
                }
            }

        }

        private void release() {
            this.isRunning = false;
            ChatUtils.close(dis, client);
        }
    }
}
