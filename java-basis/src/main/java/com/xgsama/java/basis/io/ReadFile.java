package com.xgsama.java.basis.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * ReadFile
 *
 * @author xgSama
 * @date 2021/4/26 16:45
 */
public class ReadFile {

    public static void main(String[] args) throws Exception {
        FileReader fileReader = new FileReader(new File("input/test.txt"));
        BufferedReader reader = new BufferedReader(fileReader);

        String s = reader.readLine();
        System.out.println(s);

    }
}
