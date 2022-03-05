package com.xgsama.java.basis.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * BigdataData
 *
 * @author : xgSama
 * @date : 2022/3/3 13:48:51
 */
public class BigdataData {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        String[] types = {"玩具", "饮品", "酒水", "生鲜"};
        Random random = new Random();

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://172.16.100.139:3306/bigdata?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai",
                "root",
                "abc123");
        connection.setAutoCommit(false);

        String sql = "insert into bigdata.order_info(goods_type,order_count) values (?,?);";

        int id = 1030;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < 40; i++) {

            preparedStatement.setString(1, types[Math.abs(random.nextInt() % 4)]);

            double abs = Math.abs(Math.random() + random.nextInt(1000));
            DecimalFormat df = new DecimalFormat("0.00");
            preparedStatement.setDouble(2, Double.parseDouble(df.format(abs)));

            preparedStatement.addBatch();
            System.out.println("add -----");
            TimeUnit.SECONDS.sleep(1);
        }

        preparedStatement.executeBatch();
        connection.commit();
        preparedStatement.clearBatch();

        System.out.println("success");


    }
}
