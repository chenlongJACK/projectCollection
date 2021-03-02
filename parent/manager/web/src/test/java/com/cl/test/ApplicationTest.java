package com.cl.test;

import com.cl.bean.Account;
import com.cl.dao.IAccountDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @Description
 * @auther chenlong
 * @date 2021/3/116:44
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTest {
    @Autowired
    private IAccountDao accountDao;

    @Test
    public void insertTest(){
        String uuid = UUID.randomUUID().toString();
        String id = uuid.replaceAll("-", "");
        int insert = accountDao.insert(new Account(id, "admin", "123456", "admin"));
        if(insert>0){
            System.out.println("插入成功");
        }else{
            System.out.println("插入失败");
        }
    }
}
