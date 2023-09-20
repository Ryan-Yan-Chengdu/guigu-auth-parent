package com.atguigu.system.test;


import com.atguigu.model.system.SysRole;
import com.atguigu.system.mapper.SysRoleMapper;
import com.atguigu.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class SysRoleServiceTest {
    //注入service
    @Autowired
    private SysRoleService sysRoleService;

    //批量删除
    @Test
    public void testButchDelete(){
        System.out.println("testButchDelete....");
        boolean b = sysRoleService.removeByIds(Arrays.asList(9, 10));
        if(b)
            System.out.println("删除成功");
        else
            System.out.println("删除失败");

    }
    //条件查询
    @Test
    public void testQueryWrapper(){
        QueryWrapper<SysRole> qr = new QueryWrapper<>();
//        qr.eq("role_code", "testManager");
        qr.like("role_code","ro"); //模糊查询
        List<SysRole> users = sysRoleService.list(qr);
        for(SysRole sr:users){
            System.out.println("sr="+sr);
        }
    }

    //测试根据ID删除
    @Test
    public void testDeleteById(){
        System.out.println("testDeleteById...");
        boolean b = sysRoleService.removeById(9);
        if(b)
            System.out.println("删除成功");
        else
            System.out.println("删除失败");
    }

    //测试修改
    @Test
    public void testUpdateById(){
        System.out.println("testUpdateById...");
        SysRole sr = new SysRole();
        sr.setId("9");
        sr.setDescription("测试角色new");
        boolean b = sysRoleService.updateById(sr);
        if(b)
            System.out.println("修改成功");
        else
            System.out.println("修改失败");

    }

    //添加一条
    @Test
    public void testInsert(){
        System.out.println("testInsert。。。。");
        SysRole sr = new SysRole();
        sr.setRoleName("角色操作员1409");
        sr.setRoleCode("role");
        sr.setDescription("角色操作员1409");
        boolean result = sysRoleService.save(sr);
        if(result)
            System.out.println("insert成功");
        else
            System.out.println("insert失败");

    }
    //查询所有
    @Test
    public void findAll(){

        System.out.println("-----findAll-----");
        //service方法实现
        List<SysRole> list = sysRoleService.list();
//        System.out.println(list);
        for(SysRole s: list){
            System.out.println("role="+s);
        }
    }


}
