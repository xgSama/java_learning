package com.xgsama.java.basis.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InetAddressTest
 *
 * @author : xgSama
 * @date : 2021/8/16 21:15:59
 */
public class InetAddressTest {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress xgsama = InetAddress.getByName("xgsama");
        System.out.println(xgsama.getHostName());
        System.out.println(xgsama.getHostAddress());
    }
}
