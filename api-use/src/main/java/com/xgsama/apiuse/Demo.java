package com.xgsama.apiuse;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Demo
 *
 * @author xgSama
 * @date 2021/2/22 17:08
 */
public class Demo {
    public static Integer MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {

        String s = "ssss}";
        System.out.println(s.substring(0, s.length() - 1));

//        System.out.println(tableSizeFor(10));
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;

        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
