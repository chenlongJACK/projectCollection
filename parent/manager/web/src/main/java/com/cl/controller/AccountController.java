package com.cl.controller;


import com.cl.common.bean.ResultInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @auther chenlong
 * @date 2021/3/110:45
 */
@RestController
@RequestMapping("/account")
@Api(description = "用户管理")
public class AccountController {

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public ResultInfo login(String username, String password){
        ResultInfo resultInfo=new ResultInfo();
        return resultInfo;
    }
}
