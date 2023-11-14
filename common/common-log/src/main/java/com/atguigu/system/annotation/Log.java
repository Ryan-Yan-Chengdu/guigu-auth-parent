package com.atguigu.system.annotation;

import com.atguigu.system.enums.BusinessType;
import com.atguigu.system.enums.OperatorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.*;

/**
 * @Title: Log
 * @Author Ryan
 * @Package com.atguigu.system.annotation
 * @Date 2023/11/13 16:57
 * @description: 自定义操作日志记录注解
 */
//源注解
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 模块
     */
    public String title() default "";

    /**
     * 功能
     */
    public BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    public OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;

    /**
     * 是否保存响应的参数
     */
    public boolean isSaveResponseData() default true;
}
