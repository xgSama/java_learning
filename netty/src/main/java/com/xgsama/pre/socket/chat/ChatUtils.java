package com.xgsama.pre.socket.chat;

import java.io.Closeable;

/**
 * ChatUtils
 *
 * @author xgSama
 * @date 2021/1/22 15:22
 */
public class ChatUtils {

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.client2();
    }

    public static void close(Closeable... tar) {
        for (Closeable closeable : tar) {
            try {
                if (closeable != null) {
                    closeable.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
