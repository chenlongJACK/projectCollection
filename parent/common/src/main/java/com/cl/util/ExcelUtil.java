package com.cl.util;

import com.cl.annotation.Condition;
import com.cl.annotation.Excel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description Excel工具类
 * @auther chenlong
 * @date 2021/3/3111:58
 */
public class ExcelUtil {
    /**
     * @Description 下载模板
     * @param titleNames 模板标题集合
     */
    public static HSSFWorkbook getTemplate(List<String> titleNames){
        //创建Excel工作簿
        //创建HSSFWorkbook工作薄对象
        HSSFWorkbook workbook=new HSSFWorkbook();
        //创建HSSFSheet对象
        HSSFSheet sheet=workbook.createSheet("sheet1");
        //创建行的单元格，从0开始
        HSSFRow row = sheet.createRow(0);
        //创建单元格,从0开始
        for (int i = 0; i < titleNames.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(titleNames.get(i));
        }
        return workbook;
    }

    /**
     * @Description 获取对象属性列表
     * @param module 对象类型
     */
    public static Map<String,Map<String,String>> getObjectObjectFields(String module){
        Map<String,Map<String,String>> result=new HashMap<>();
        String className ="";
        switch (module){
            case "account": className="com.cl.bean.Account";
                break;
            default: break;
        }
        Class<?> aClass=null;
        try {
             aClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if(aClass!=null) {
            Field[] fields = aClass.getDeclaredFields();
            Map<String, String> fieldNames = new HashMap<>();
            result.put("fieldNames",fieldNames);
            Map<String, String> conditionFieldNames = new HashMap<>();
            result.put("conditionFieldNames",conditionFieldNames);
            for (int i = 0; i < fields.length; i++) {
                Excel excel = fields[i].getAnnotation(Excel.class);
                Condition condition = fields[i].getAnnotation(Condition.class);
                if (excel != null) {
                    fieldNames.put(fields[i].getName(), excel.value());
                }
                if (condition != null) {
                    conditionFieldNames.put(fields[i].getName(), condition.value());
                }
            }
            return result;
        }
        return null;
    }

    /**
     * @Description 将excel文件转换为指定对象集合
     * @param fileName excel文件名称
     * @param is    excel文件流
     * @param tClass class对象
     * @param fieldNames 属性名与excel标题映射集合
     * @param <T>
     * @return
     */
    public static <T> List<T> getObjectListFromExcel(String fileName, InputStream is, Class<T> tClass, Map<String,String> fieldNames){
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
                    //字符串类型
                    if(type==String.class) {
                        row1.getCell(i1).setCellType(Cell.CELL_TYPE_STRING);
                    }
                    //数字
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
}
