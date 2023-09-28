package com.atguigu.system.mapper;

import com.atguigu.model.system.SysRole;
import com.atguigu.model.system.SysUser;
import com.atguigu.model.vo.SysRoleQueryVo;
import com.atguigu.model.vo.SysUserQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-09-27
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    //条件分页查询
    IPage<SysUser> selectPage(Page<SysUser> pagePram, @Param("vo")SysUserQueryVo sysUserQueryVo);
}
