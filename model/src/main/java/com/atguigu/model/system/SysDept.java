package com.atguigu.model.system;

import com.atguigu.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "部门")
@TableName("sys_dept")
public class SysDept extends BaseEntity {
    private static final Long serialVersionUID = 1L;

    @TableField("name")
    @ApiModelProperty(value = "部门名称")
    private String name;

    @TableField("parent_id")
    @ApiModelProperty(value = "上级部门id")
    private Long parentId;

    @TableField("tree_path")
    @ApiModelProperty(value = "树结构")
    private String treePath;

    @TableField("sort_value")
    @ApiModelProperty(value = "排序")
    private Integer SortValue;

    @TableField("leader")
    @ApiModelProperty(value = "负责人")
    private String leader;

    @TableField("phone")
    @ApiModelProperty(value = "电话")
    private String phone;

    @TableField("status")
    @ApiModelProperty(value = "状态(1正常,0停用)")
    private Integer status;

    @TableField(exist = false)
    @ApiModelProperty(value = "下级部门")
    private List<SysDept> children;
}
