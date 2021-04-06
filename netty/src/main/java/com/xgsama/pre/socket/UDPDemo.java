package com.xgsama.pre.socket;

import org.junit.Test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDPDemo
 *
 * @author xgSama
 * @date 2021/1/22 14:11
 */
public class UDPDemo {

    @Test
    public void sender() throws Exception {
        DatagramSocket socket = new DatagramSocket();

        String str = "UDP发送测试";
        byte[] data = str.getBytes();

        InetAddress ip = InetAddress.getLocalHost();
        DatagramPacket packet = new DatagramPacket(data, 0, data.length, ip, 2233);

        socket.send(packet);

        socket.close();
    }

    @Test
    public void receiver() throws Exception {
        DatagramSocket socket = new DatagramSocket(2233);

        byte[] buf = new byte[512];
        DatagramPacket packet = new DatagramPacket(buf, 0, buf.length);

        socket.receive(packet);

        System.out.println(new String(packet.getData(), 0, packet.getLength()));

        socket.close();
    }
}
