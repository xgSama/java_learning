package com.xgsama.java.basis;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
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
@Slf4j
public class Test {

    public static void main(String[] args) throws IOException {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
    }

    public static void test() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);//设置星期一为一周开始的第一天
        calendar.setMinimalDaysInFirstWeek(4);//可以不用设置
        calendar.setTimeInMillis(System.currentTimeMillis());//获得当前的时间戳
        int weekYear = calendar.get(Calendar.YEAR);//获得当前的年
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);//获得当前日期属于今年的第几周
        System.out.println(weekYear + "\t" + weekOfYear);
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
