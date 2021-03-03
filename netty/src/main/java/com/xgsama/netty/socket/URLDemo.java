package com.xgsama.netty.socket;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * URLDemo
 *
 * @author xgSama
 * @date 2021/1/22 14:30
 */
public class URLDemo {

    public static void main(String[] args) throws Exception {
        URL url = new URL("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F23%2F15414%2Ff03de3393c71be19.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1613889329&t=8f3863553cecff805aa39668d432c022");

        URLConnection urlConnection = url.openConnection();

        urlConnection.connect();

        InputStream inputStream = urlConnection.getInputStream();

        FileOutputStream fileOutputStream = new FileOutputStream(new File("F:\\JAVA\\java_learning\\input\\url.png"));

        byte[] buf = new byte[512];
        int len;
        while ((len = inputStream.read(buf)) != -1) {
            fileOutputStream.write(buf, 0, len);
        }


    }
}
