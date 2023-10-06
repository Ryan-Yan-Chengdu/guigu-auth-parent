package com.atguigu.system.utils;

import com.atguigu.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: MenuHelper
 * @Author Ryan
 * @Package com.atguigu.system.utils
 * @Date 2023/10/4 0:43
 * @description: MenuHelper工具类，菜单列表使用
 */
public class MenuHelper {
    //构建树型结构
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        //创建集合封装最终数据
        List<SysMenu> trees = new ArrayList<>();
        //遍历所有菜单集合
        for (SysMenu sysMenu: sysMenuList) {
            //找到递归入口， parentid=0
            if(sysMenu.getParentId().longValue()==0){
                trees.add(findChildren(sysMenu,sysMenuList));
            }
        }
        return trees;
    }

    //从根节点进行递归查询，查询子节点
    //判断id=parentid是否相同,如果相同，是子节点，进行数据封装
    //为了方便，设置为私有静态类
    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> treeNodes) {
        //数据初始化
        sysMenu.setChildren(new ArrayList<SysMenu>());
        //遍历递归查找
        for (SysMenu it : treeNodes) {
            //获取当前菜单id
            String id = sysMenu.getId();
            long cid = Long.parseLong(id);
            //获取所有菜单的parentid
            Long parentId = it.getParentId();
            //对比
            if(cid==parentId){
                if(sysMenu.getChildren()==null){
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(findChildren(it, treeNodes));
            }
        }
        return sysMenu;
    }


}
