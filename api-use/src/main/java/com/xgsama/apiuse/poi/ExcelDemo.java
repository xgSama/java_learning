package com.xgsama.apiuse.poi;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.File;
import java.util.ArrayList;

/**
 * ExcelDemo
 *
 * @author: xgsama
 * @date: 2022-04-23 14:06:47
 */
public class ExcelDemo {

    public static void main(String[] args) {

        Integer rowNumber = 0;
        String[] titles = {"id", "name", "password"};

        // 第一步，创建一个workbook，对应一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();


        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet hssfSheet = workbook.createSheet("sheet1");


        HSSFRow titleRow = hssfSheet.createRow(rowNumber++);
        System.out.println(titleRow);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = hssfSheet.createRow(rowNumber++);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
        hssfCellStyle.setAlignment(HorizontalAlignment.CENTER);
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight((short) 1000);
        hssfCellStyle.setFont(font);


        HSSFCell hssfCell = null;
        for (int i = 0; i < titles.length; i++) {
            hssfCell = row.createCell(i);//列索引从0开始
            hssfCell.setCellValue(titles[i]);//列名1
            hssfCell.setCellStyle(hssfCellStyle);//列居中显示
        }

        // 第五步，写入实体数据
        Student person1 = new Student("1", "张三", "123");
        Student person2 = new Student("2", "李四", "123");
        Student person3 = new Student("3", "王五", "123");
        Student person4 = new Student("4", "徐小筱", "123");

        //这里我把list当做数据库啦
        ArrayList<Student> list = new ArrayList<>();
        list.add(person1);
        list.add(person2);
        list.add(person3);
        list.add(person4);

        for (Student student : list) {
            row = hssfSheet.createRow(rowNumber++);

            // 第六步，创建单元格，并设置值
            String id = null;
            if (student.getId() != null) {
                id = student.getId();
            }
            row.createCell(0).setCellValue(id);
            String name = "";
            if (student.getUsername() != null) {
                name = student.getUsername();
            }
            row.createCell(1).setCellValue(name);
            String password = "";
            if (student.getPassword() != null) {
                password = student.getPassword();
            }
            row.createCell(2).setCellValue(password);
        }

        // 第七步，将文件输出
        try {
            workbook.write(new File("input/sss.xls"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
