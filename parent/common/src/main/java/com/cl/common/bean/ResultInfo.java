package com.cl.common.bean;

import java.util.List;

/**
 * @Description 请求响应实体类
 * @auther chenlong
 * @date 2021/3/211:12
 */
public class ResultInfo {

    private String code;
    private String msg;
    private Object data;

    public ResultInfo() {
    }

    public ResultInfo(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
