package com.cl.dao;

import com.cl.bean.Account;

/**
 * @Description
 * @auther chenlong
 * @date 2021/3/113:24
 */
public interface IAccountDao {

    /**
     * @Description 新增
     * @param user
     * @return
     */
    int insert(Account user);

}
