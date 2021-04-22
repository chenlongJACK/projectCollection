package com.cl.util;

import java.util.UUID;

/**
 * @Description
 * @auther chenlong
 * @date 2021/4/2216:26
 */
public class UUIDUtil {
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }
}
