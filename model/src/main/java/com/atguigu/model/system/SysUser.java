package com.atguigu.model.system;

import com.atguigu.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "用户")
@TableName("sys_user")
public class SysUser extends BaseEntity {

    private static final long serialVersionUID=1L;

    @TableField("username")
    @ApiModelProperty(value = "用户名")
    private String username;

    @TableField("password")
    @ApiModelProperty(value = "密码")
    private String password;

    @TableField("name")
    @ApiModelProperty(value = "姓名")
    private String name;

    @TableField("phone")
    @ApiModelProperty(value = "手机")
    private String phone;

    @TableField("head_url")
    @ApiModelProperty(value = "头像地址")
    private String headUrl;

    @TableField("dept_id")
    @ApiModelProperty(value = "部门id")
    private Long deptId;

    @TableField("post_id")
    @ApiModelProperty(value = "岗位id")
    private Long postId;

    @TableField("description")
    @ApiModelProperty(value = "描述" )
    private String description;

    @TableField("status")
    @ApiModelProperty(value = "状态 (1：正常 0：停用)")
    private Integer status;

    @TableField(exist = false)
    private List<SysRole> roleList;

    //岗位
    @TableField(exist = false)
    private String postName;

    //部门
    @TableField(exist = false)
    private String deptName;


}
