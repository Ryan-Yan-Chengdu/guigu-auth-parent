package com.atguigu.system.service.impl;


import com.atguigu.model.system.SysUser;
import com.atguigu.system.custom.CustomUser;
import com.atguigu.system.service.SysMenuService;
import com.atguigu.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**D
 * @Title: UserDetailsServiceImpl
 * @Author Ryan
 * @Package com.atguigu.system.service.impl
 * @Date 2023/11/5 23:58
 * @description:
 */
@Component
public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getUserInfoByUserName(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if(sysUser.getStatus().intValue()==0){
            throw new RuntimeException("用户已被禁用");
        }

        //根据用户ID查询操作权限数据
        List<String> userPermsList = sysMenuService.getUserButtonList(sysUser.getId());
        //转换grantSecurity要求格式数据
        List<SimpleGrantedAuthority>  authorities = new ArrayList<>();
        for(String perms:userPermsList){
            authorities.add(new SimpleGrantedAuthority(perms.trim()));
        }
        return new CustomUser(sysUser, authorities);
    }
}
