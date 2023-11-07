package com.atguigu.system.custom;

import com.atguigu.common.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Title: CustomMD5Password
 * @Author Ryan
 * @Package com.atguigu.system.custom
 * @Date 2023/11/5 23:46
 * @description: 自定义密码组件
 */
//自定义密码组件
@Component
public class CustomMD5Password implements PasswordEncoder {
    public String encode(CharSequence rawPassword) {
        return MD5.encrypt(rawPassword.toString());
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5.encrypt(rawPassword.toString()));
    }
}
