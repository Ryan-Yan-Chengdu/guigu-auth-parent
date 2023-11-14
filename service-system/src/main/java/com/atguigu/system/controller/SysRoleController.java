package com.atguigu.system.controller;

import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysRole;
import com.atguigu.model.vo.AssignRoleVo;
import com.atguigu.model.vo.SysRoleQueryVo;
import com.atguigu.system.exception.GuiguException;
import com.atguigu.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;
    // http://localhost:8800/admin/system/sysRole/findAll

    @ApiOperation("获取用户角色数据")
    @GetMapping("toAssign/{userId}")
    public Result toAssign(@PathVariable String userId) {
        Map<String, Object> roleMap  = sysRoleService.getRolesById(userId);
        return Result.ok(roleMap);
    }

    @ApiOperation("用户分配角色")
    @PostMapping("doAssign")
    public Result  doAssign(@RequestBody AssignRoleVo assignRoleVo){
        sysRoleService.doAssign(assignRoleVo);
        return Result.ok();
    }


    //7.批量删除
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
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
    @PreAuthorize("hasAuthority('bnt.sysRole.update')")
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
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation("根据id查询")
    @PostMapping("findRoleById/{id}")
    public Result findRoleById(@PathVariable Long id){
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }

    //4.添加
    @PreAuthorize("hasAuthority('bnt.sysRole.add')")
    //@RequestBody，不能使用get提交方式，
    //传递json格式数据，把json格式数据封装到对象里面{...}
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
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
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
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
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
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation("查询所有记录")
    @GetMapping("findAll")
    public Result findAllRole(){
        //TODO 模拟异常效果
        /*try{
            int i = 9/0;
        }catch (Exception e){
            //手动抛出异常
            throw new GuiguException(20001,"我执行了自定义异常处理");
        }*/

        //调用service
        List<SysRole> list = sysRoleService.list();
        return Result.ok(list);

    }
}
