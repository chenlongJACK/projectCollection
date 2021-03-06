package com.cl.bean;

import com.cl.annotation.Excel;
import org.apache.ibatis.type.Alias;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @Description
 * @auther chenlong
 * @date 2021/3/111:25
 */
@Alias("account")
public class Account {
    /**主键Id*/
    private String id;
    /**用户名*/
    @NotBlank(message = "用户名不能为空")
    @Excel("用户名")
    private String username;
    /**密码*/
    @NotBlank(message = "密码不能为空")
    @Excel("密码")
    private String password;
    /**姓名*/
    @NotBlank(message = "姓名不能为空")
    @Excel("姓名")
    private String name;

    public Account() {
    }

    public Account(String id, String username, String password, String name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
