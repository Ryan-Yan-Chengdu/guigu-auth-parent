package com.atguigu.system.aspect;

import com.atguigu.system.annotation.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Title: LogAspect
 * @Author Ryan
 * @Package com.atguigu.system.aspect
 * @Date 2023/11/13 17:40
 * @description: 操作日志记录处理
 */

@Aspect

@Component
public class LogAspect {
    @AfterReturning(pointcut = "@annotation(controllerLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Log controllerLog, Object jsonResult) {
        handleLog(joinPoint, controllerLog, null, jsonResult);
    }
}
