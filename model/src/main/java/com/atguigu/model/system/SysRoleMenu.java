package com.atguigu.model.system;

import com.atguigu.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "角色菜单")
@TableName("sys_role_menu")
public class SysRoleMenu extends BaseEntity {
    private static final long serialVersionUID=1L;

    @TableField("role_id")
    @ApiModelProperty(value = "角色id")
    private String roleId;

    @TableField("menu_id")
    @ApiModelProperty(value = "菜单id")
    private String menuId;
}
