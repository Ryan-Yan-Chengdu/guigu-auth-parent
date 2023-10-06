package com.atguigu.system.service.impl;

import com.atguigu.model.system.SysMenu;

import com.atguigu.model.system.SysRoleMenu;
import com.atguigu.model.vo.AssignMenuVo;
import com.atguigu.system.exception.GuiguException;
import com.atguigu.system.mapper.SysMenuMapper;
import com.atguigu.system.mapper.SysRoleMenuMapper;
import com.atguigu.system.service.SysMenuService;
import com.atguigu.system.utils.MenuHelper;
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
        //1.获取所有菜单 status=1

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
}
