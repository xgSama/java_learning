package com.xgsama.java.basis.jdbc;

import java.sql.*;

/**
 * MysqlTest
 *
 * @author 霜风折叶
 * @version 1.0
 * @date 2020/10/19 11:53
 */
public class MysqlTest {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://47.103.218.168:3307/test", "root", "cyz19980815");

        String sql = "select * from test";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();


        while (resultSet.next()) {
            System.out.print(resultSet.getInt(1) + "\t");
            System.out.print(resultSet.getString(2) + "\t");
            if (resultSet.getString(2) == null){
                System.out.println(0);
            }
        }

        ps.close();
        connection.close();

    }
}
