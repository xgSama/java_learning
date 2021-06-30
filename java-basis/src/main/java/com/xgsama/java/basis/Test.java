package com.xgsama.java.basis;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Test
 *
 * @author 霜风折叶
 * @version 1.0
 * @date 2020/10/11 15:26
 */
public class Test {

    public static void main(String[] args) throws IOException {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        Stream<String> result = numbers.stream()
                .sorted((i1, i2) -> i2 - i1)
                .flatMap((Function<Integer, Stream<String>>) integer -> Stream.of(integer + "wwww"));

        result.forEach(System.out::println);
    }

//    public static double abs(double a) {
//        return (a <= 0.0D) ? 0.0D - a : a;
//    }
//
//    public static double sqrt(double c) {
//        if (c < 0) return Double.NaN;
//        double err = 1e-15;
//        double t = c;
//        while (abs(t - c / t) > err * t)// 也就是1-c/(t^2)=err
//            t = (c / t + t) / 2.0;
//        return t;
//    }
}
