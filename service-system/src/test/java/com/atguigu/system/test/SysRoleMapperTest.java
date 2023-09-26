package com.atguigu.system.test;


import com.atguigu.model.system.SysRole;
import com.atguigu.system.mapper.SysRoleMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class SysRoleMapperTest {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    //7.条件删除
    @Test
    public void testDelete(){
        System.out.println("条件删除：");
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.eq("role_name","用户管理员");
        int result = sysRoleMapper.delete(wrapper);
        System.out.println("result:"+result);

    }

    //6.条件查询
    @Test
    public void testSelect(){
        System.out.println("条件查询");
        //先创建一个条件构造器对象
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        //设置条件
//        wrapper.eq("role_name","用户管理员");
        wrapper.like("role_name","管理");
        //调用方法查询
        List<SysRole> list = sysRoleMapper.selectList(wrapper);
        System.out.println("list:"+list);
    }
    //5.批量删除
    @Test
    public void testBatchDelete(){
        System.out.println("批量删除");
        int i = sysRoleMapper.deleteBatchIds(Arrays.asList(8, 9));
        System.out.println("result:"+i);

    }
    //4.删除操作
    @Test
    public void deleteId(){
        System.out.println("普通删除：");
        int i = sysRoleMapper.deleteById(10);
        System.out.println("result:" + i);

    }
    //3.修改操作
    @Test
    public void update(){
        System.out.println("修改操作：");
        //根据id查询
        SysRole sysRole = sysRoleMapper.selectById(1);
        //设置修改值
        sysRole.setDescription("系统管理员尚硅谷");
        //调用方法实现修改
        int result = sysRoleMapper.updateById(sysRole);
        System.out.println("result:"+result);

    }

    //2.添加操作
    @Test
    public void add(){
        System.out.println("添加操作2.");
        SysRole sysRole = new SysRole();

        sysRole.setDescription("测试角色2");
        sysRole.setRoleCode("testManager");
        sysRole.setRoleName("测试角色2");

        int rows = sysRoleMapper.insert(sysRole);
        System.out.println("rows:"+rows);
    }
    //1.查询表所有记录
    @Test
    public void findAll(){
        System.out.println("查询表里所有记录");
        List<SysRole> lists = sysRoleMapper.selectList(null);
        for (SysRole sysRole : lists) {
            System.out.println(sysRole);
        }
    }
}
