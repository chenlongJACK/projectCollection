package com.cl.controller;


import com.cl.bean.Account;
import com.cl.bean.util.FieldCollection;
import com.cl.common.bean.ResultInfo;
import com.cl.dao.IAccountDao;
import com.cl.service.IRoleService;
import com.cl.util.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Description
 * @auther chenlong
 * @date 2021/3/110:45
 */
@RestController
@RequestMapping("/account")
@Api(description = "用户管理")
public class AccountController {

    private static Logger logger=LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private IRoleService roleService;
    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public ResultInfo login(@Validated Account account){
        account.setId(UUID.randomUUID().toString().replaceAll("-",""));

        ResultInfo resultInfo=new ResultInfo();
        return resultInfo;
    }
    @RequestMapping("upload")
    public ResultInfo upload(@RequestPart MultipartFile file, @RequestPart FieldCollection fieldCollection){
        ResultInfo resultInfo=new ResultInfo();
        String className ="";
        Class aClass = null;
        String object = fieldCollection.getObject();
        switch (object){
            case "account": className="com.cl.bean.Account";
                break;
            case "role": className="com.cl.bean.Role";
                break;
            default: break;
        }
        try {
            aClass = Class.forName(className);
            resultInfo = roleService.updateByExcel(file.getOriginalFilename(),file.getInputStream(),aClass,fieldCollection.getValuefields());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultInfo;
    }
    @RequestMapping("/getTemplateFields")
    public ResultInfo getTemplateFields(String project){
        ResultInfo resultInfo=new ResultInfo();
        Map<String, String> account = ExcelUtil.getObjectObjectFields(project);
        resultInfo.setData(account);
        return resultInfo;
    }

    @RequestMapping("/getTemplate")
    public void getTemplate(HttpServletResponse response,@RequestBody List<String> titleNames,String fileName) throws IOException {
        ResultInfo resultInfo=new ResultInfo();
        HSSFWorkbook workbook = ExcelUtil.getTemplate(titleNames);
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
    }
}
