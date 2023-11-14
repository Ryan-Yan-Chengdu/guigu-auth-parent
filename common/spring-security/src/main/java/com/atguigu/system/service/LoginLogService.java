package com.atguigu.system.service;

/**
 * @Title: LoginLogService
 * @Author Ryan
 * @Package com.atguigu.system.service
 * @Date 2023/11/10 16:20
 * @description: 登录日志接口
 */
public interface LoginLogService {
    //登录日志
    public void recordLoginLog(String username, Integer status,
                               String ipaddr, String message);
}
