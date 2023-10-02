package com.atguigu.system.service.impl;

import com.atguigu.model.system.SysRole;
import com.atguigu.model.system.SysUserRole;
import com.atguigu.model.vo.AssignRoleVo;
import com.atguigu.model.vo.SysRoleQueryVo;
import com.atguigu.system.mapper.SysRoleMapper;
import com.atguigu.system.mapper.SysUserRoleMapper;
import com.atguigu.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    //条件分页查询
    @Override
    public IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo) {
        IPage<SysRole> pageModel = baseMapper.selectPage(pageParam, sysRoleQueryVo);
        return pageModel;
    }

    //获取用户的角色数据
    @Override
    public Map<String, Object> getRolesById(String userId) {
        //获取所有角色
        List<SysRole> list = baseMapper.selectList(null);
        //根据用户id查询，已经分配角色
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<SysUserRole> userRolesList = sysUserRoleMapper.selectList(wrapper);
        //从userRoles集合获取所有角色id
        List<String> userRoleIds=new ArrayList<>();
        for (SysUserRole userRole :userRolesList) {
            String roleId = userRole.getRoleId();
            userRoleIds.add(roleId);
        }
        //封装到map集合
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("allRoles",list);//所有角色
        returnMap.put("userRoleIds",userRoleIds); //用户分配角色id集合
        return returnMap;

    }
    //给用户分配角色
    @Override
    public void doAssign(AssignRoleVo assignRoleVo) {
        //根据用户id删除之前分配的角色
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();

        wrapper.eq("user_id", assignRoleVo.getUserId());
        sysUserRoleMapper.delete(wrapper);
        //获取所有角色id。添加角色用户关系表
        //角色id列表
        List<String> roleIdList = assignRoleVo.getRoleIdList();
        for (String roleId: roleIdList) {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(assignRoleVo.getUserId());
            userRole.setRoleId(roleId);
            sysUserRoleMapper.insert(userRole);
        }
    }
}
