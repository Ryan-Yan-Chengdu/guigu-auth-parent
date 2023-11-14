package com.atguigu.system.mapper;

import com.atguigu.model.system.SysLoginLog;
import com.atguigu.model.system.SysUser;
import com.atguigu.model.vo.SysLoginLogQueryVo;
import com.atguigu.model.vo.SysUserQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-09-27
 */
//Repository：数据访问层
@Repository
//Mapper：动态创建对象
@Mapper
public interface LoginLogMapper extends BaseMapper<SysLoginLog> {

}
