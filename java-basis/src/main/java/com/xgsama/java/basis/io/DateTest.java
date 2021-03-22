package com.xgsama.java.basis.io;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * DateTest
 *
 * @author xgSama
 * @date 2020/12/22 18:37
 */
public class DateTest {

    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar(2019, Calendar.SEPTEMBER, 1, 0, 0, 0);
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");


        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        System.out.println(calendar.before(now));


        while (calendar.before(now)) {

            System.out.print(calendar.get(Calendar.YEAR) + "\t");
            System.out.print(calendar.get(Calendar.MONTH) + 1 + "\t");
            System.out.print(calendar.get(Calendar.DAY_OF_MONTH) + "\t");
            System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
            calendar.add(Calendar.HOUR, 1);
        }

    }
}
