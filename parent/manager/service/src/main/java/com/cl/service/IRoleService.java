package com.cl.service;

import com.cl.bean.Role;
import com.cl.common.bean.ResultInfo;

import java.io.InputStream;
import java.util.Map;

/**
 * @Description
 * @auther chenlong
 * @date 2021/4/2216:01
 */
public interface IRoleService {
    ResultInfo insert(Role role);

    ResultInfo updateByExcel(String fileName, InputStream is, Class<Role> tClass, Map<String,String> fieldNames);
}
