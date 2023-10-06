package com.atguigu.model.system;

import com.atguigu.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "菜单")
@TableName("sys_menu")
public class SysMenu  extends BaseEntity {
    private static final long serialVersionUID=1L;

    @TableField("parent_id")
    @ApiModelProperty(value = "所属上级")
    private Long parentId;

    @TableField("name")
    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "类型(1:菜单，2:按钮)")
    @TableField("type")
    private Integer type;

    @TableField("path")
    @ApiModelProperty(value = "路由地址")
    private String path;

    @TableField("component")
    @ApiModelProperty(value = "组件路径")
    private String component;

    @TableField("perms")
    @ApiModelProperty(value = "权限标识")
    private String perms;

    @TableField("icon")
    @ApiModelProperty(value = "图标")
    private String icon;

    @TableField("sort_value")
    @ApiModelProperty(value = "排序")
    private Integer sortValue;

    @TableField("status")
    @ApiModelProperty(value = "状态（0：禁止，1：正常）")
    private Integer status;


    //下级列表
    @TableField(exist = false)//表示表里面没有对应字段，不写上会和类上的注释冲突，报错
    private List<SysMenu> children;

    //是否选中
    @TableField(exist=false)
    private boolean isSelected;

}
