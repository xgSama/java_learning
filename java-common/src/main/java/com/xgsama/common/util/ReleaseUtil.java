package com.xgsama.common.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * ReleaseUtil
 *
 * @author : xgSama
 * @date : 2021/11/9 21:00:08
 */
public class ReleaseUtil {
    private ReleaseUtil() {
    }

    public static void release(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
