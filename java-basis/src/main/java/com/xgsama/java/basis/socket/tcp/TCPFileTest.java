package com.xgsama.java.basis.socket.tcp;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * TCPFileTest
 *
 * @author : xgSama
 * @date : 2021/8/17 10:47:48
 */
public class TCPFileTest {

    String inputPath = "/Users/xgSama/IdeaProjects/java_learning/input/";

    @Test
    public void test() {
        System.out.println(System.getProperty("user.dir"));
    }

    @Test
    public void client() throws IOException {
        Socket socket = new Socket("localhost", 9090);
        OutputStream outputStream = socket.getOutputStream();
        FileInputStream fis = new FileInputStream(inputPath + "server.png");
        byte[] buffer = new byte[1024];
        int length = -1;
        while ((length = fis.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }
        socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        System.out.println(bufferedReader.readLine());

        fis.close();
        outputStream.close();
        socket.close();
    }

    @Test
    public void server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(9090);

        Socket accept = serverSocket.accept();
        InputStream inputStream = accept.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(inputPath + "server.tcp.png");

        byte[] buffer = new byte[1024];
        int length = -1;
        while ((length = inputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, length);
        }

        accept.shutdownInput();


        OutputStream outputStream = accept.getOutputStream();
        outputStream.write("我已收到！".getBytes(StandardCharsets.UTF_8));

        fileOutputStream.close();
        inputStream.close();
        accept.close();
        serverSocket.close();

    }
}
