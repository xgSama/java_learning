package com.xgsama.netty.socket;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * TCPDemo
 *
 * @author xgSama
 * @date 2021/1/22 10:37
 */
@SuppressWarnings("DuplicatedCode")
public class TCPDemo {

    // InetAddress
    @Test
    public void ipTest1() throws Exception {
        InetAddress inet1 = InetAddress.getByName("47.103.218.168");
        System.out.println(inet1);

        InetAddress inet2 = InetAddress.getByName("www.baidu.com");
        System.out.println(inet2);

        InetAddress inet3 = InetAddress.getLocalHost();
        System.out.println(inet3);

    }

    @Test
    public void client() {
        InetAddress ip = null;
        Socket socket = null;
        OutputStream os = null;
        try {
            ip = InetAddress.getByName("localhost");
            socket = new Socket(ip, 2233);
            os = socket.getOutputStream();

            os.write("I'm client \n".getBytes());
            os.write("How are you \n".getBytes());
            os.write("你都弄死你都撒撒擦 \n".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void server() {

        ServerSocket server = null;
        Socket socket = null;
        BufferedReader bufferedReader = null;

        try {
            server = new ServerSocket(2233);
            socket = server.accept();
            bufferedReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            System.out.println("收到了" + socket.getInetAddress().getHostAddress());
            System.out.println("收到了" + socket.getInetAddress().getHostName());
            System.out.println("收到了" + Arrays.toString(socket.getInetAddress().getAddress()));


            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Test
    public void fileClient() {
        InetAddress ip = null;
        Socket socket = null;
        BufferedOutputStream os = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            ip = InetAddress.getByName("localhost");
            socket = new Socket(ip, 2233);

            os = new BufferedOutputStream(socket.getOutputStream());
            FileInputStream file = new FileInputStream(new File("F:\\JAVA\\java_learning\\input\\test.png"));
            bufferedInputStream = new BufferedInputStream(file);


            byte[] buf = new byte[512];
            int len = -1;

            while ((len = bufferedInputStream.read(buf)) != -1) {
                os.write(buf);
            }
            os.flush();

            // 关闭数据输出
            socket.shutdownOutput();

            // TODO 接收
            InputStream inputStream = socket.getInputStream();
            byte[] buf2 = new byte[512];
            int len1 = -1;

            while ((len1 = inputStream.read(buf2)) != -1) {
                System.out.println(new String(buf2));
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void fileServer() {

        ServerSocket server = null;
        Socket socket = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            server = new ServerSocket(2233);
            socket = server.accept();


            // TODO 接收客户端数据
            bis = new BufferedInputStream(socket.getInputStream());
            System.out.println("收到了" + socket.getInetAddress().getHostAddress());

            FileOutputStream fos = new FileOutputStream(new File("F:\\JAVA\\java_learning\\input\\server.png"));
            bos = new BufferedOutputStream(fos);

            byte[] buf = new byte[512];
            int len = -1;
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf);
            }

            // TODO 返回客户端消息
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("accept".getBytes());


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
