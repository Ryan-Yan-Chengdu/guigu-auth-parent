package com.atguigu.system.controller;

import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysRole;
import com.atguigu.model.vo.SysRoleQueryVo;
import com.atguigu.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;
    // http://localhost:8800/admin/system/sysRole/findAll

    //7.批量删除
    @ApiOperation("批量删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> list){
        boolean result = sysRoleService.removeByIds(list);
        if(result){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    //6.修改，最终修改
    @ApiOperation("最终修改")
    @PostMapping("update")
    public Result updateRole(@RequestBody SysRole sysRole){
        boolean result = sysRoleService.updateById(sysRole);
        if(result){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    //5.修改，根据id查询
    @ApiOperation("根据id查询")
    @PostMapping("findRoleById/{id}")
    public Result findRoleById(@PathVariable Long id){
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }

    //4.添加
    @ApiOperation("添加角色")
    @PostMapping("save")
    public Result saveRole(@RequestBody SysRole sysRole){
        boolean result = sysRoleService.save(sysRole);
        if(result){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }


    //3.条件分页查询
    //page:当前页数，limit：每页的记录数
    @ApiOperation("条件分页查询")
    @GetMapping("{page}/{limit}")
    public Result findPageQueryRole(@PathVariable Long page,
                                    @PathVariable Long limit,
                                    SysRoleQueryVo sysRoleQueryVo){
        //创建Page对象
        Page<SysRole> pageParam = new Page<>(page,limit);
        //调用service方法
        IPage<SysRole> pageModel = sysRoleService.selectPage(pageParam, sysRoleQueryVo);
        //返回
        return Result.ok(pageModel);

    }

    //2.逻辑删除接口
    @ApiOperation("逻辑删除接口")
    @DeleteMapping("remove/{id}")
    public Result removeRole(@PathVariable Long id){
        //调用方法删除
        boolean isSuccess = sysRoleService.removeById(id);
        if(isSuccess)
            return Result.ok();
        else
            return Result.fail();
    }


    //1.查询所有记录
    @ApiOperation("查询所有记录")
    @GetMapping("findAll")
    public Result findAllRole(){
        //调用service
        List<SysRole> list = sysRoleService.list();
        return Result.ok(list);

    }
}
