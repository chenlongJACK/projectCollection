package com.cl.utils;

import com.cl.annotation.Excel;
import com.cl.common.bean.ResultInfo;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.Inet4Address;
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
     * 下载模板
     */
    public static void downLoadTemplate(Class objClass,String templateName){
        //获取pojo上的有Excel注解的属性
        Field[] fields = objClass.getDeclaredFields();
        List<String> list=new ArrayList<>();
        for (Field field : fields) {
            Excel annotation = field.getAnnotation(Excel.class);
            if(annotation!=null&&(annotation.type().equals(Excel.Type.ALL)||annotation.type().equals(Excel.Type.INPUT))){
                    list.add(annotation.value());
            }
        }
        //创建Excel工作簿
        //创建HSSFWorkbook工作薄对象
        HSSFWorkbook workbook=new HSSFWorkbook();
        //创建HSSFSheet对象
        HSSFSheet sheet=workbook.createSheet("sheet1");
        //创建行的单元格，从0开始
        HSSFRow row = sheet.createRow(0);
        //创建单元格,从0开始
        for (int i = 0; i < list.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(list.get(i));
        }
        //文档输出
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("D:\\我的文档\\"+templateName+".xls");
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void upLoad(Class objClass){
        String filePath="D:\\我的文档\\测试.xls";
        FileInputStream fis=null;
        try {
            fis=new FileInputStream(filePath);
            Workbook workbook = null;
            if (filePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (filePath.endsWith(".xls") || filePath.endsWith(".et")) {
                workbook = new HSSFWorkbook(fis);
            }
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(0);
            //根据标题行设置对应类的序号映射集合
            Map<String,Integer> cellMap=new HashMap<>();
            for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                cellMap.put(String.valueOf(row.getCell(i)),i);
            }
            //设置列序号与pojo属性的映射集合
            Field[] fields = objClass.getDeclaredFields();
            Map<Integer,Field> fieldMap=new HashMap<>();
            for (Field field : fields) {
                Excel annotation = field.getAnnotation(Excel.class);
                if (annotation != null && (annotation.type().equals(Excel.Type.ALL) || annotation.type().equals(Excel.Type.INPUT))) {
                    // 设置类的私有字段属性可访问.
                    field.setAccessible(true);
                    fieldMap.put(cellMap.get(annotation.value()), field);
                }
            }
            //创建对象的集合
            List<Object> list=new ArrayList<>();
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row1 = sheet.getRow(i);
                Object object = objClass.newInstance();
                for (int i1 = 0; i1 < row1.getPhysicalNumberOfCells(); i1++) {
                    Field field = fieldMap.get(i1);
                    Class<?> type = field.getType();
                    if(type==String.class) {//字符串类型
                        row1.getCell(i1).setCellType(Cell.CELL_TYPE_STRING);
                    }
                    field.set(object,row1.getCell(i1).getStringCellValue());
                }
                list.add(object);
            }
            //其他操作
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        upLoad(ResultInfo.class);
    }
}
