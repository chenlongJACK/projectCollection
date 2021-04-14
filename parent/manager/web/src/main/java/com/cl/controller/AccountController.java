package com.cl.controller;


import com.cl.bean.Account;
import com.cl.common.bean.ResultInfo;
import com.cl.dao.IAccountDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private IAccountDao accountDao;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public ResultInfo login(@Validated Account account){
        account.setId(UUID.randomUUID().toString().replaceAll("-",""));
        accountDao.insert(account);
        ResultInfo resultInfo=new ResultInfo();
        return resultInfo;
    }
}
