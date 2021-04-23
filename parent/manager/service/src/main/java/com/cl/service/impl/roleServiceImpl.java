package com.cl.service.impl;

import com.cl.bean.Role;
import com.cl.common.bean.ResultInfo;
import com.cl.dao.IRoleDao;
import com.cl.service.IRoleService;
import com.cl.util.ExcelUtil;
import com.cl.util.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;
import java.util.Map;


/**
 * @Description
 * @auther chenlong
 * @date 2021/4/2216:03
 */
@Service
public class roleServiceImpl implements IRoleService {
    @Resource
    private IRoleDao roleDao;

    @Override
    public ResultInfo insert(Role role) {

        return null;
    }

    @Override
    public ResultInfo updateByExcel(String fileName, InputStream is, Class<Role> tClass, Map<String, String> fieldNames) {
        ResultInfo resultInfo=new ResultInfo();
        List<Role> roles = ExcelUtil.getObjectListFromExcel(fileName, is, tClass, fieldNames);
        int row=0;
        try {
            for (Role role : roles) {
                role.setId(UUIDUtil.getUUID());
                row += roleDao.insert(role);
            }
            resultInfo.setCode("200");
            resultInfo.setData(row);
        }catch (Exception e){
            resultInfo.setCode("500");
        }
        return resultInfo;
    }
}
