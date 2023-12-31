package com.atguigu.system.service.impl;

import com.atguigu.model.system.SysMenu;

import com.atguigu.model.system.SysRoleMenu;
import com.atguigu.model.vo.AssignMenuVo;
import com.atguigu.model.vo.RouterVo;
import com.atguigu.system.exception.GuiguException;
import com.atguigu.system.mapper.SysMenuMapper;
import com.atguigu.system.mapper.SysRoleMenuMapper;
import com.atguigu.system.service.SysMenuService;
import com.atguigu.system.utils.MenuHelper;
import com.atguigu.system.utils.RouterHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-02
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;


    //菜单列表（树形）
    @Override
    public List<SysMenu> findNodes() {
        //获取所有菜单
        List<SysMenu> sysMenuList = baseMapper.selectList(null);
        //所有菜单数据转换要求数据格式
        List<SysMenu> resultList = MenuHelper.buildTree(sysMenuList);
        //所有菜单数据转换要求数据格式

        return resultList;
    }

    //删除菜单
    @Override
    public void removeMenuById(String id) {
        //查询当前删除菜单下面是否有子菜单
        //根据id=parentid
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(wrapper);
        if(count>0){
            //表示有子菜单
            throw new GuiguException(201,"请删除子菜单");
        }
        //调用删除
        baseMapper.deleteById(id);

    }

    //根据角色分配菜单
    @Override
    public List<SysMenu> findMenuByRoleId(String roleId) {
        //1.获取所有菜单 status=1的权限列表

        QueryWrapper<SysMenu> wrapperMenu = new QueryWrapper<>();
        wrapperMenu.eq("status", 1);
        List<SysMenu> menuList = baseMapper.selectList(wrapperMenu);

        //2.根据角色id查询 角色分配过的菜单列表
        QueryWrapper<SysRoleMenu> sysRoleMenuQueryWrapper = new QueryWrapper<>();
        sysRoleMenuQueryWrapper.eq("role_id",roleId);
        List<SysRoleMenu> roleMenus = sysRoleMenuMapper.selectList(sysRoleMenuQueryWrapper);

        //3.从第二步查询列表中，获取角色分配所有菜单id
        List<String> roleMenuIds = new ArrayList<>();
        for (SysRoleMenu sysRoleMenu : roleMenus) {
            roleMenuIds.add(sysRoleMenu.getMenuId());
        }

        //4.数据处理， isSelect 如果菜单选中 true， 否则false
        // 拿着分配菜单id和所有菜单对比，有相同的，就让isSelect值为true
        for (SysMenu sysMenu : menuList) {
            if(roleMenuIds.contains(sysMenu.getId())){
                //设置该权限已被分配
                sysMenu.setSelected(true);
            }else{
                sysMenu.setSelected(false);
            }
        }

        //5.转换成树形结构，为了最终显示MenuHelper方法实现
        List<SysMenu> sysMenus = MenuHelper.buildTree(menuList);

        return sysMenus;
    }

    //给角色分配菜单权限
    @Override
    public void doAssign(AssignMenuVo assignMenuVo) {

        //根据角色id删除菜单权限
        QueryWrapper<SysRoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",assignMenuVo.getRoleId());
        sysRoleMenuMapper.delete(wrapper);


        //遍历菜单id列表，一个个进行添加
        List<String> menuIdList = assignMenuVo.getMenuIdList();
        for (String menuId: menuIdList) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(assignMenuVo.getRoleId());
            sysRoleMenuMapper.insert(sysRoleMenu);
        }
    }
    //根据userid查询菜单权限值
    @Override
    public List<RouterVo> getUserMenuList(String userId) {
        //admin是超级管理员，操作所有内容
        List<SysMenu> sysMenuList = null;
        //判断userid值是1代表超级管理员，查询所有权限数据
        if("1".equals(userId)){
            QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
            wrapper.eq("status",1);
            wrapper.orderByAsc("sort_value");

            sysMenuList = baseMapper.selectList(wrapper);
        }else {

            //如果userid不是1，表示其他类型用户，查询这个用户权限
            sysMenuList = baseMapper.findMenuListUserId(userId);
        }

        //构建树形结构
        List<SysMenu> sysMenusTreeList= MenuHelper.buildTree(sysMenuList);

        //转变为前端路由要求的数据格式
        List<RouterVo> routerVoList = RouterHelper.buildRouters(sysMenusTreeList);

        return routerVoList;
    }
    //根据userid查询按钮权限值
    @Override
    public List<String> getUserButtonList(String userId) {

        List<SysMenu> sysMenuList =null;
        //判断是否是管理员
        if("1".equals(userId)){
            sysMenuList = baseMapper.selectList(new QueryWrapper<SysMenu>().eq("status", 1));
        }else{
            sysMenuList = baseMapper.findMenuListUserId(userId);

        }
        //sysMenuList遍历
        List<String> permissionList = new ArrayList<>();
        for (SysMenu sysMenu: sysMenuList) {
            //0:目录 1:菜单 2:按钮
            //type=2
            //获取菜单权限值方法“getUserMenuList”里面，
            //调用的RouterHelper类里面已经有“menu.getType().intValue() == 1”判断
            if(sysMenu.getType()==2){
                String perms = sysMenu.getPerms();
                permissionList.add(perms);
            }
        }
        return permissionList;
    }
}
