package com.cl.user.dao;

import com.cl.user.bean.User;

/**
 * @Description
 * @auther chenlong
 * @date 2021/3/113:24
 */
public interface IUserDao {

    /**
     * @Description 新增
     * @param user
     * @return
     */
    int insert(User user);

}
