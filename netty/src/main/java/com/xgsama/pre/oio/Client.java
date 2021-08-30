package com.xgsama.pre.oio;

import java.io.*;
import java.net.Socket;

/**
 * Client
 *
 * @author : xgSama
 * @date : 2021/8/16 20:10:51
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9978);

        // 要发送给服务器的信息
        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        pw.write("客户端发送信息");
        pw.flush();

        socket.shutdownOutput();


        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String info = null;
        while ((info = br.readLine()) != null) {
            System.out.println(info);
        }

        br.close();
        is.close();
        os.close();
        pw.close();
        socket.close();


    }
}
