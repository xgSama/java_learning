package com.xgsama.jvm.load;

import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.runtime.JSONFunctions;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * LoadJDBCDriver
 *
 * @author : xgSama
 * @date : 2021/12/10 10:49:08
 */
public class LoadJDBCDriver {
    public static void main(String[] args) throws Exception {
        String path = "/Users/xgSama/.m2/repository/mysql/mysql-connector-java/5.1.47/mysql-connector-java-5.1.47.jar";

        byte[] decode = Base64.getDecoder().decode("eyJwYXNzd29yZCI6IlRlc3Rmb3J1c2VyMjAyMSIsImpkYmNVcmwiOiJqZGJjOm15c3FsOi8vcm0tYnAxMDY2MWcyMTdpNHplOTlpby5teXNxbC5yZHMuYWxpeXVuY3MuY29tOjMzMDYvdGVzdGZvcnVzZXIiLCJ1c2VybmFtZSI6InJkc190ZXN0In0=");
        String pass = new String(decode,  StandardCharsets.UTF_8);
        System.out.println(pass);


        String quote = JSONParser.quote(pass);

        System.out.println(quote);



        loadJarPath(path);

//        Class.forName("com.mysql.jdbc.Driver");


//        Connection conn = DriverManager.getConnection("jdbc:mysql://xgsama:3307/test?useSSL=false", "root", "cyz19980815");
//        //3.操作数据库，实现增删改查
//        Statement stmt = conn.createStatement();
//        ResultSet rs = stmt.executeQuery("SELECT id,name,aid FROM book");
//        //如果有数据，rs.next()返回true
//        while (rs.next()) {
//            System.out.println(rs.getInt("id") + " 年龄：" + rs.getString("name") + rs.getInt("aid"));
//        }
    }


    /**
     * URLClassLoader的addURL方法
     */
    private static Method addURL = initAddMethod();

    /**
     * 初始化方法
     */
    private static final Method initAddMethod() {
        try {
            Method add = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            add.setAccessible(true);
            return add;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static URLClassLoader system = (URLClassLoader) ClassLoader.getSystemClassLoader();

    /**
     * 循环遍历目录，找出所有的JAR包
     */
    private static final void loopFiles(File file, List<File> files) {
        if (file.isDirectory()) {
            File[] tmps = file.listFiles();
            for (File tmp : tmps) {
                loopFiles(tmp, files);
            }
        } else {
            if (file.getAbsolutePath().endsWith(".jar") || file.getAbsolutePath().endsWith(".zip")) {
                files.add(file);
            }
        }
    }

    /**
     * 加载JAR文件
     *
     * @param file
     */
    public static void loadJarFile(File file) {
        try {
            addURL.invoke(system, file.toURI().toURL());
            System.out.println("加载JAR包：" + file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从一个目录加载所有JAR文件
     *
     * @param path
     */
    public static final void loadJarPath(String path) {
        List<File> files = new ArrayList<>();
        File lib = new File(path);
        loopFiles(lib, files);
        for (File file : files) {
            loadJarFile(file);
        }
    }


}
