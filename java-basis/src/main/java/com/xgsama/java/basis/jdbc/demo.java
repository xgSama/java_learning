package com.xgsama.java.basis.jdbc;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * demo
 *
 * @author : xgSama
 * @date : 2022/3/3 14:16:22
 */
public class demo {
    public static void main(String[] args) {

        Random random = new Random();
        for (int i = 0; i < 10; i++) {

            double abs = Math.abs(Math.random() + random.nextInt(1000));
            DecimalFormat df = new DecimalFormat("0.00");
            String str = df.format(abs);
            System.out.println(str);
        }

    }
}
