package com.cl.test;

import com.cl.bean.Account;
import com.cl.bean.Role;
import com.cl.dao.IAccountDao;
import com.cl.dao.IRoleDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private IRoleDao roleDao;
    @Test
    @Transactional(rollbackFor = RuntimeException.class)
    public void insertAccountTest(){
        String uuid = UUID.randomUUID().toString();
        String id = uuid.replaceAll("-", "");
        int insert = accountDao.insert(new Account(id, "admin", "123456", "admin"));
        Assert.assertEquals(1,insert);
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void insertRoleTest(){
        String uuid = UUID.randomUUID().toString();
        String id = uuid.replaceAll("-", "");
        int insert = roleDao.insert(new Role(id, "admin"));
        Assert.assertEquals(1,insert);
    }
}
