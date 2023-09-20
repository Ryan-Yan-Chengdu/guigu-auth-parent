package com.atguigu.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 */
@SpringBootApplication
@MapperScan("com.atguigu.system.mapper")
public class ServiceAuthApplication {
    public static void main(String[] args) {
        System.out.println("I love JAVA!");
        SpringApplication.run(ServiceAuthApplication.class, args);
    }
}
