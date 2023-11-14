package com.atguigu.system.service.impl;

import com.atguigu.model.system.SysLoginLog;
import com.atguigu.system.mapper.LoginLogMapper;
import com.atguigu.system.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title: LoginLogServiceImpl
 * @Author Ryan
 * @Package com.atguigu.system.service.impl
 * @Date 2023/11/12 22:09
 * @description: 登录日志实现类
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    LoginLogMapper loginLogMapper;

    @Override
    public void recordLoginLog(String username, Integer status, String ipaddr, String message) {
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setUsername(username);
        sysLoginLog.setStatus(status);
        sysLoginLog.setIpaddr(ipaddr);
        sysLoginLog.setMsg(message);

        loginLogMapper.insert(sysLoginLog);

    }
}
