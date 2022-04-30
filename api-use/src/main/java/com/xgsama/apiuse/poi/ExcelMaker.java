package com.xgsama.apiuse.poi;

import org.apache.commons.validator.Var;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.checkerframework.checker.units.qual.A;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ExcelMaker
 *
 * @author: xgsama
 * @date: 2022-04-23 18:58:27
 */
public class ExcelMaker {

    private static Weekly weekly = new Weekly();

    static {
        List<WeeklyItem> tasks = new ArrayList<>();
        WeeklyItem weeklyItem1 = new WeeklyItem(1L, "cc", "cc的问题1", null, false, null, 1, 1, LocalDateTime.now(), LocalDateTime.now());
        WeeklyItem weeklyItem2 = new WeeklyItem(2L, "cc", "cc的问题2", null, true, "人艰不拆", 1, 1, LocalDateTime.now(), LocalDateTime.now());
        WeeklyItem weeklyItem3 = new WeeklyItem(3L, "bb", "bb的问题1", null, false, null, 1, 1, LocalDateTime.now(), LocalDateTime.now());
        WeeklyItem weeklyItem4 = new WeeklyItem(4L, "aa", "aa的问题1", null, false, null, 1, 1, LocalDateTime.now(), LocalDateTime.now());
        WeeklyItem weeklyItem5 = new WeeklyItem(5L, "dd", "dd的问题1", null, false, null, 0, 1, LocalDateTime.now(), LocalDateTime.now());
        tasks.add(weeklyItem1);
        tasks.add(weeklyItem2);
        tasks.add(weeklyItem3);
        tasks.add(weeklyItem4);
        tasks.add(weeklyItem5);

        Stream<WeeklyItem> stream = tasks.stream();

        List<WeeklyItem> current =
                tasks.stream()
                        .filter(item -> item.getStatus() == 1)
                        .sorted()
                        .collect(Collectors.toList());
        weekly.setCurrent(current);

        List<WeeklyItem> next = tasks.stream()
                .filter(item -> item.getStatus() == 1)
                .sorted()
                .collect(Collectors.toList());
        weekly.setNext(next);

        List<WeeklyItem> diff = tasks.stream()
                .filter(WeeklyItem::getIsDifficulty)
                .sorted()
                .collect(Collectors.toList());
        weekly.setDiff(diff);
        weekly.setStartDate(LocalDate.of(2022, 4, 18));
        weekly.setEndDate(LocalDate.of(2022, 4, 22));


    }


//    public static void main(String[] args) throws IOException {
//
//        XSSFWorkbook template = new XSSFWorkbook("input/temp.xlsx");
//        XSSFSheet sheet = template.getSheetAt(0);
//
//        int firstRowNum = sheet.getFirstRowNum();
//        int currentRow = 0;
//
//        cur:
//        while (true) {
//            XSSFRow row = sheet.getRow(firstRowNum++);
//            Iterator<Cell> cellIterator = row.cellIterator();
//            while (cellIterator.hasNext()) {
//                String stringCellValue = cellIterator.next().getStringCellValue();
//                if (stringCellValue.equals("${current.name}")) {
//                    currentRow = --firstRowNum;
//                    break cur;
//                }
//            }
//        }
//
//        int size = weekly.getCurrent().size();
//        int size1 = weekly.get().size();
//
//        sheet.shiftRows(currentRow + 1, sheet.getLastRowNum(), size1 + size + 1);
//        XSSFRow row1 = sheet.getRow(currentRow);
//
//        Map<String, List<WeeklyItem>> stringListMap = weekly.get();
//        for (Map.Entry<String, List<WeeklyItem>> entry : stringListMap.entrySet()) {
//            String customer = entry.getKey();
//            for (WeeklyItem weeklyItem : entry.getValue()) {
//                XSSFRow row = sheet.createRow(++firstRowNum);
//                row.copyRowFrom(row1, new CellCopyPolicy.Builder().build());
//                Iterator<Cell> cellIterator = row.cellIterator();
//
//                while (cellIterator.hasNext()) {
//                    Cell next = cellIterator.next();
//                    String v = next.getStringCellValue();
//                    if (v.equals("${current.name}")) {
//                        next.setCellValue(customer);
//                    }
//                    if (v.equals("${current.desc}")) {
//                        next.setCellValue(weeklyItem.getDesc());
//                    }
//                    if (v.equals("${current.status}")) {
//                        next.setCellValue(weeklyItem.getStatus());
//                    }
//                    if (v.equals("${current.remarks}")) {
//                        next.setCellValue(weeklyItem.getRemarks());
//                    }
//                }
//            }
//            XSSFRow row = sheet.createRow(++firstRowNum);
//            row.copyRowFrom(row1, new CellCopyPolicy.Builder().build());
//            Iterator<Cell> cellIterator = row.cellIterator();
//            while (cellIterator.hasNext()) {
//                Cell next = cellIterator.next();
//                String v = next.getStringCellValue();
//                if (v.equals("${current.name}")) {
//                    next.setCellValue(customer);
//                }
//                if (v.equals("${current.desc}")) {
//                    next.setCellValue("本周共完成n个任务");
//                }
//            }
//
//        }
//
//
//        template.write(new FileOutputStream("input/temp3.xlsx"));
//
//
//    }

    public static void test() throws Exception {
        replaceMaker(weekly);
    }


    public static List<String> findMaker(String cellValue) {

        List<String> marks = new ArrayList<>();

        findMaker(cellValue, marks);

        return marks;

    }

    private static void findMaker(String cellValue, List<String> res) {
        int left = cellValue.indexOf("${");
        int right = cellValue.indexOf('}', left + 1);


        if (!checkMark(cellValue, left, right)) {
            return;
        }

        String mark = cellValue.substring(left, right + 1);
        res.add(mark);

        if (right + 1 < cellValue.length()) {
            findMaker(cellValue.substring(right), res);
        }
    }

    public static <T> List<Object> replaceMark(String str, int left, int right, T obj) {
        StringBuilder stringBuilder = new StringBuilder(str);
        String mark = str.substring(left, right);

        String[] split = mark.split("\\.");
        if (split.length == 1) {
            try {
                Method method = obj.getClass().getMethod(toGetterName(split[0]));
                String value = method.invoke(obj).toString();


                ArrayList<Object> res = new ArrayList<>();

                stringBuilder.replace(left, right, value);

                res.add(stringBuilder.toString());
                res.add(value.length());


                return res;
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }

        }

        return null;

    }

    public static String toGetterName(String field) {
        char[] chars = field.toCharArray();
        char c = chars[0];
        if (c >= 'a' && c <= 'z') {
            chars[0] -= 32;
        }
        return "get" + String.valueOf(chars);
    }

    public static boolean checkMark(String maker, int left, int right) {

        return left != -1 && right > left;

    }

    public static void main(String[] args) throws Exception {
//        replaceMaker(weekly);
//        System.out.println(toGetterName("_startDate"));
//        System.out.println(replaceMark("endDate", 0, "endDate".length(), weekly));
//
//        System.out.println(findMaker("${sss}---${aaaa}"));
//
//        System.out.println(replaceSimpleMark("${startDate}", "${startDate}", weekly));

        GenerateXlsx(weekly);


    }

    public static <T> void replaceMaker(T c) throws Exception {
        Method getStartDate = c.getClass().getMethod("getStartDate");

        Object invoke = getStartDate.invoke(c);
        System.out.println(invoke);

    }

    public static <T> void GenerateXlsx(T obj) throws Exception {
        XSSFWorkbook template = new XSSFWorkbook("input/temp.xlsx");
        XSSFSheet sheet = template.getSheetAt(0);

        int currentRow = sheet.getFirstRowNum();

        end:
        while (true) {

            XSSFRow row = sheet.getRow(currentRow);
            if (row == null) {
                currentRow++;
                continue;
            }

            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell curCell = cellIterator.next();
                String stringCellValue = curCell.getStringCellValue();

                if (stringCellValue.equals("${tempend}")) {
                    curCell.setCellValue("");
                    break end;
                }

                List<String> marks = findMaker(stringCellValue);

                // 只有两种合法的mark ${xx} ${xxx.xxx}
                if (isSimpleMark(marks)) {
                    for (String mark : marks) {
                        stringCellValue = replaceSimpleMark(stringCellValue, mark, obj);
                    }
                    curCell.setCellValue(stringCellValue);
                } else {
                    // ${xxx.xxx}
                    if (!(curCell.getColumnIndex() == row.getFirstCellNum())) {
                        throw new RuntimeException(stringCellValue + "：错误的标记位置");
                    }
                    replaceMultiMark(marks.get(0), row, obj);
                    break;
                }
            }
            System.out.println(currentRow);
            currentRow++;
        }

        template.write(new FileOutputStream("input/zhoubao.xlsx"));
    }

    public static boolean isSimpleMark(List<String> makers) {
        int size = makers.size();

        if (size == 1 && makers.get(0).split("\\.").length > 1) {
            return false;
        }

        return true;
    }

    /**
     * 进入前先判断标记是否合法
     *
     * @param mark
     * @param row
     * @param obj
     * @param <T>
     * @return 替换后的尾行行号
     */
    public static <T> String replaceMultiMark(String mark, XSSFRow row, T obj) {
        int rowNum = row.getRowNum();
        String[] split = mark.substring(2, mark.length() - 1).split("\\.");
        String filedName = split[0];
        String valueKey = split[1];
        Class<?> aClass = obj.getClass();

        try {
            // TODO 确定下这个列表是不是需要进行分组操作
            Field field = aClass.getDeclaredField(filedName);
            boolean needToKeyBy = isNeedToKeyBy(field);

            Method method = aClass.getMethod(toGetterName(filedName));
            Object invoke = method.invoke(obj);

            if (needToKeyBy) {
                // 进行分组操作

            } else {
                // 直接开始干活
                // 传入值，和当前行，进行替换个复制
                entityToRow(row, (List<?>) invoke);
            }


        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            return mark;
        }


        return "";
    }


    public static Integer entityToRow(XSSFRow rowToCopy, List<?> data) {

        XSSFRow tempRow = rowToCopy;

        // 需要复制这些行
        int size = data.size();
        int currentRowNum = rowToCopy.getRowNum();
        XSSFSheet thisSheet = rowToCopy.getSheet();

        if (size == 0) {
            // TODO 全换成空
            System.out.println("全换成空");
            return currentRowNum;
        }

        Class<?> dataClass = data.get(0).getClass();


        // rowToCopy是需要复制的模板
        Iterator<Cell> cellIterator = rowToCopy.cellIterator();

        Map<String, List<Integer>> markMap = new HashMap<>();
        while (cellIterator.hasNext()) {
            Cell next = cellIterator.next();
            String stringCellValue = next.getStringCellValue();
            List<String> maker = findMaker(stringCellValue);
            if (maker.size() == 0) continue;
            for (String s : maker) {
                List<Integer> integers = markMap.get(s);
                if (integers == null) {
                    List<Integer> integers1 = new ArrayList<>();
                    integers1.add(next.getColumnIndex());
                    markMap.put(s, integers1);
                } else {
                    integers.add(next.getColumnIndex());
                }
            }
        }


        if (size > 1) {
            thisSheet.shiftRows(rowToCopy.getRowNum() + 1, thisSheet.getLastRowNum(), size - 1);
        }

        CellCopyPolicy copyPolicy = new CellCopyPolicy.Builder().build();
        for (int i = 1; i < size; i++) {
            thisSheet.copyRows(currentRowNum, currentRowNum, currentRowNum + i, copyPolicy);
        }

        System.out.println("sheet 下移 and 复制");


        for (Object datum : data) {
            // 创建一行，第一行也重新创建
            XSSFRow row = thisSheet.getRow(currentRowNum++);
//            row.copyRowFrom(rowToCopy, copyPolicy);
            for (Map.Entry<String, List<Integer>> entry : markMap.entrySet()) {
                String key = entry.getKey();
                List<Integer> cellIndex = entry.getValue();

                String[] split = key.split("\\.");
                String mark = split[1].substring(0, split[1].length() - 1);
                String s = toGetterName(mark);
                try {
                    Method method = dataClass.getMethod(s);
                    Object invoke = method.invoke(datum);
                    String newValue = cellValueConverter(invoke);

                    for (Integer index : cellIndex) {
                        XSSFCell cell = row.getCell(index);
                        String stringCellValue = cell.getStringCellValue();
                        String replace = stringCellValue.replace(key, newValue);
                        cell.setCellValue(replace);
                    }

                } catch (ReflectiveOperationException e) {
                    e.printStackTrace();
                }

            }
        }

        return currentRowNum - 1;

    }

    public static boolean isNeedToKeyBy(Field field) {
        RowKey annotation = field.getAnnotation(RowKey.class);
        if (annotation == null) {
            return false;
        }
        String key = annotation.key();

//        return !key.equals("");

        return false;
    }


    public static <T> String replaceSimpleMark(String str, String mark, T obj) {
        String markValue = mark.substring(2, mark.length() - 1);

        String getter = toGetterName(markValue);

        try {
            Method method = obj.getClass().getMethod(getter);
            Object invoke1 = method.invoke(obj);

            String invoke = cellValueConverter(invoke1);
            return str.replace(mark, invoke);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            return str;
        }
    }


    public static String cellValueConverter(Object object) {
        if (object instanceof TemporalAccessor) {

            return DateTimeFormatter.ofPattern("yyyyMMdd").format((TemporalAccessor) object);
        }


        return object == null ? "" : object.toString();

    }
}
