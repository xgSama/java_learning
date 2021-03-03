package com.xgsama.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * CommonUtil
 *
 * @author xgSama
 * @date 2021/2/2 10:38
 */
public class CommonUtil {
    private CommonUtil() {}

    public static void release(Closeable ...closeables) {
        for (Closeable closeable : closeables) {
            if (closeable!= null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
