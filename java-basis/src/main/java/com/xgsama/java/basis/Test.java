package com.xgsama.java.basis;

import com.xgsama.util.param.ParamUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;

/**
 * Test
 *
 * @author 霜风折叶
 * @version 1.0
 * @date 2020/10/11 15:26
 */
public class Test {
    public static void main(String[] args) throws Exception {
//        Integer a1 = new Integer(12);
//        Integer a2 = new Integer(12);
//        Integer b1 = -129;
//        Integer b2 = -129;
//        Integer c1 = -128;
//        Integer c2 = -128;
//        System.out.println(a1 == a2);   //false
//        System.out.println(b1==b2);     //false
//        System.out.println(c1 == c2);   //true

//        HashMap<String, String> map = new HashMap<>();
//        List<String> list = Arrays.asList("Aa", "BB", "C#");
//
//        for (String s : list) {
//            System.out.println(s.hashCode());
//            map.put(s, s);
//        }
//        map.size();

//        Map<String, String> map = ParamUtil.fromArgs(args);
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + ":" + entry.getValue());
//        }
        int res = 0;

        for (int i = 7; i <= 9997; i++) {
            String s = Integer.toString(i);

            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '7') {
                    res++;
                }
            }

        }

        System.out.println(res);


    }

    public static double abs(double a) {
        return (a <= 0.0D) ? 0.0D - a : a;
    }

    public static double sqrt(double c) {
        if (c < 0) return Double.NaN;
        double err = 1e-15;
        double t = c;
        while (abs(t - c / t) > err * t)// 也就是1-c/(t^2)=err
            t = (c / t + t) / 2.0;
        return t;
    }
}
