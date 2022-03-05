package com.xgsama.java.basis.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * TimeUtil
 *
 * @author xgSama
 * @date 2021/4/28 16:01
 */
public class TimeUtil {

    public static final DateTimeFormatter COMMON_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String dateToString(Date time) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(time.toInstant(), ZoneId.systemDefault());
        return dateToString(localDateTime);
    }

    public static String dateToString(LocalDateTime time) {
        return time.format(COMMON_FORMATTER);
    }

    public static void main(String[] args) {
          final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);


        System.out.println(formatter.format(LocalDateTime.now(ZoneId.of("UTC"))));

    }
}
