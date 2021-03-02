package com.cl.controller;


import com.cl.common.bean.ResultInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @auther chenlong
 * @date 2021/3/110:45
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @RequestMapping("login")
    public ResultInfo login(String username, String password){
        ResultInfo resultInfo=new ResultInfo();
        return resultInfo;
    }
}
