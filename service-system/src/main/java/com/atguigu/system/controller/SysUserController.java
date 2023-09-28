package com.atguigu.system.controller;


import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysUser;
import com.atguigu.model.vo.SysUserQueryVo;
import com.atguigu.system.service.SysUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-09-27
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("用户列表")
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable Long page,
                       @PathVariable Long limit,
                       SysUserQueryVo sysUserQueryVo){//SysUserQueryVo是查询条件,封装条件

        //创建page对象
        Page<SysUser> pageParam = new Page<>(page,limit);
        //调用service方法
        IPage<SysUser> pageModel = sysUserService.selectPage(pageParam,sysUserQueryVo);

        return Result.ok(pageModel);
    }

    @ApiOperation("添加用户")
    @PostMapping("save")
    public Result save(@RequestBody SysUser user){
        boolean is_Success = sysUserService.save(user);
        if(is_Success){
            return Result.ok();
        }else{
            return Result.fail().message("添加用户失败");
        }
    }
    @ApiOperation("根据id查询")
    @GetMapping("getUser/{id}")
    public Result getUser(@PathVariable String id){//因为后面BaseEntity的id修改为String，长度比Long长
        SysUser sysUser = sysUserService.getById(id);
        return Result.ok(sysUser);
    }

    @ApiOperation("最终修改")
    @PostMapping("update")
    public Result update(@RequestBody SysUser sysUser){
        boolean is_Success = sysUserService.updateById(sysUser);
        if(is_Success){
            return Result.ok();
        }else{
            return Result.fail().message("修改失败");
        }
    }

    @ApiOperation("逻辑删除接口")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id){//因为后面BaseEntity的id修改为String，长度比Long长
        boolean is_Success = sysUserService.removeById(id);
        if(is_Success){
            return Result.ok();
        }else{
            return Result.fail().message("删除失败");
        }
    }

    @ApiOperation("批量删除接口")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<String> list){
        boolean is_Success = sysUserService.removeByIds(list);
        if(is_Success){
            return Result.ok();
        }else{
            return Result.fail().message("批量删除失败");
        }
    }
}

