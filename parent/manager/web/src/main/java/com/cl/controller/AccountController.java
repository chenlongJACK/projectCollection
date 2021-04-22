package com.cl.controller;


import com.cl.bean.Account;
import com.cl.bean.util.FieldCollection;
import com.cl.common.bean.ResultInfo;
import com.cl.dao.IAccountDao;
import com.cl.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
}
