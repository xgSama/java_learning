package com.xgsama.java.basis.io;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * FileUtil
 *
 * @author xgSama
 * @date 2020/11/10 21:12
 */
@SuppressWarnings("all")
public class FileUtil {
    public static void main(String[] args) throws Exception {
//        RandomAccessFile s = new RandomAccessFile(new File("java_basis/pom.xml"), "r");
//        read(s, null, 0, 100);

//        copy1("input/pom.xml", "input/pom.xml.copy1");
//        copy3(new File("input/pom.xml"), new File("input/pom3.xml"));
//
      jiemi(new File("input/pom3.xml"));

    }

    public static void split(File source, File target, int blockNum) {

        long length = source.length();
    }

    public static void read(RandomAccessFile source, File target, int beginPos, int actualSize) throws Exception {
        source = new RandomAccessFile(new File("input/pom.xml"), "r");

        // 随机读取
        source.seek(beginPos);
        byte[] flush = new byte[1024 * 10];
        int len = -1;

        while ((len = source.read(flush)) != -1) {
            if (actualSize > len) {
                System.out.print(new String(flush, 0, len));
                actualSize -= len;
            } else {
                System.out.print(new String(flush, 0, actualSize));
                break;
            }
        }

        source.close();

    }

    public static void copy(String path1, String path2) throws Exception {
        copy(new File(path1), new File(path2));
    }

    public static void copy(File from, File dest) throws IOException {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        if (!from.exists()) {
            throw new IOException("源文件: " + from.getAbsolutePath() + " 不存在");
        }

        try {
            bis = new BufferedInputStream(new FileInputStream(from));
            bos = new BufferedOutputStream(new FileOutputStream(dest));

            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (null != bos) {
                    bos.close();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (null != bis) {
                    bis.close();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void copy1(String from, String dest) throws IOException {
        copy1(new File(from), new File(dest));
    }

    public static void copy1(File from, File dest) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(from));
        BufferedWriter bw = new BufferedWriter(new FileWriter(dest));


        char[] flush = new char[1024];
        int len = -1;
        String str;
        while ((str = bf.readLine()) != null) {
            bw.write(str);

        }

        while ((len = bf.read(flush)) != -1) {
            bw.write(flush, 0, len);
        }

        bw.close();
        bf.close();

    }


    public static void copy3(File from, File dest) throws IOException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        BufferedReader bf = new BufferedReader(new FileReader(from));
        BufferedWriter bw = new BufferedWriter(new FileWriter(dest));


        String str;
        while ((str = bf.readLine()) != null) {

//            try {
//                System.out.println(jiami(str));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            bw.write(jiami(str));

        }


        bw.close();
        bf.close();

    }


    private static String jiami(String string) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        //设置密钥长度
        keyGenerator.init(128);
        //生成密钥对象
        SecretKey secretKey = keyGenerator.generateKey();
        //获取密钥
        byte[] keyBytes = secretKey.getEncoded();
        //key转换
        Key key = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        //初始化，设置为加密
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] result = cipher.doFinal(string.getBytes());

        System.out.println(Hex.encodeHexString(result));

        cipher.init(Cipher.DECRYPT_MODE, key);
        result = cipher.doFinal(result);
        System.out.println("jdk des decrypt:" + new String(result));

        return Hex.encodeHexString(result);
    }

    private static KeyGenerator keyGenerator = null;

    public static void init() throws Exception {
        keyGenerator =  KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
    }

    private static void jiemi(File file) throws Exception {

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        //设置密钥长度
        keyGenerator.init(128);
        //生成密钥对象
        SecretKey secretKey = keyGenerator.generateKey();
        //获取密钥
        byte[] keyBytes = secretKey.getEncoded();
        //key转换
        Key key = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String str;

        while ((str = reader.readLine()) != null) {
            System.out.println(str);
            System.out.println(Arrays.toString(cipher.doFinal(str.getBytes())));
        }


    }

}
