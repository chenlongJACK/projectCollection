package com.cl.util;

import com.cl.annotation.Excel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.omg.CORBA.portable.InputStream;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    public static <T> void updateByExcel(MultipartFile file,Class<T> tClass,Map<String,String> fieldNames){
        try {
            String name = file.getName();
            Workbook workbook = null;
            if (name.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(file.getInputStream());
            } else if (name.endsWith(".xls")) {
                workbook = new HSSFWorkbook(file.getInputStream());
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
                String fileName = fieldNames.get(field.getName());
                // 设置类的私有字段属性可访问.
                field.setAccessible(true);
                fieldMap.put(cellMap.get(fileName), field);
            }

        }catch (Exception e){

        }
    }
    public static void main(String[] args) {
        returnObjectFields("account");
    }
}
