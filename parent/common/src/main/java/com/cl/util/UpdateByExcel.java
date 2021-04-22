package com.cl.util;

import com.cl.annotation.Excel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @auther chenlong
 * @date 2021/4/1516:05
 */
public class UpdateByExcel {

    public static void returnObjectFields(String module){
        String className ="";
        Class<?> aClass = null;
        switch (module){
            case "account": className="com.cl.bean.Account";
                break;
            default: break;
        }
        try {
            aClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Field[] fields = aClass.getDeclaredFields();
        HashMap<String,String> fieldNames=new HashMap<>();
        for (int i = 0; i < fields.length; i++) {
            Excel annotation = fields[i].getAnnotation(Excel.class);
            if(annotation!=null) {
                fieldNames.put(fields[i].getName(),annotation.value());
            }
        }
        System.out.println(fieldNames);
    }

    public static <T> List<T> updateByExcel(String fileName, InputStream is,Class<T> tClass, Map<String,String> fieldNames){
        try {
            Workbook workbook = null;
            if (fileName.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(is);
            } else if (fileName.endsWith(".xls")) {
                workbook = new HSSFWorkbook(is);
            }
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(0);
            //根据标题行设置对应类的序号映射集合
            Map<String, Integer> cellMap = new HashMap<>();
            for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                cellMap.put(String.valueOf(row.getCell(i)), i);
            }
            //设置列序号与pojo属性的映射集合
            Field[] fields = tClass.getDeclaredFields();
            Map<Integer,Field> fieldMap=new HashMap<>();
            for (Field field : fields) {
                //获取传入更新列的中文描述
                String fieldName = fieldNames.get(field.getName());
                //设置类的私有字段属性可访问.
                field.setAccessible(true);
                //设置列号与pojo属性映射关系
                fieldMap.put(cellMap.get(fieldName), field);
            }
            //创建对象的集合
            List<T> list=new ArrayList<>();
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i <physicalNumberOfRows; i++) {
                Row row1 = sheet.getRow(i);
                T t = tClass.newInstance();
                int physicalNumberOfCells = row1.getPhysicalNumberOfCells();
                for (int i1 = 0; i1 <physicalNumberOfCells ; i1++) {
                    Field field = fieldMap.get(i1);
                    Class<?> type = field.getType();
                    if(type==String.class) {//字符串类型
                        row1.getCell(i1).setCellType(Cell.CELL_TYPE_STRING);
                    }
                    if(type==Integer.class){
                        row1.getCell(i1).setCellType(Cell.CELL_TYPE_NUMERIC);
                    }
                    field.set(t,row1.getCell(i1).getStringCellValue());
                }
                list.add(t);
            }
            return list;
        }catch (Exception e){
            throw new RuntimeException("系统异常");
        }
    }
    public static void main(String[] args) {
        returnObjectFields("account");
    }
}
