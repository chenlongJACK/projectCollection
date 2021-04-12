package com.cl.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description Excel导入导出
 * @auther chenlong
 * @date 2021/3/3111:49
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Excel {

    /***/
    String value();
    /**描述*/
    String description() default "";
    /**是否可以为空*/
    boolean isNuLL() default true;
    /** 允许导入或导出*/
    Type type() default Type.ALL;

    enum Type{
        ALL,INPUT,OUTPUT
    }
}
