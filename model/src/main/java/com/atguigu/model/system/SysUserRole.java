package com.atguigu.model.system;

import com.atguigu.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "用户角色")
@TableName("sys_user_role")
public class SysUserRole extends BaseEntity {
    private static final long serialVersionUID=1L;

    @TableField("role_id")
    @ApiModelProperty(value = "角色id")
    private String roleId;

    @TableField("user_id")
    @ApiModelProperty(value = "用户id")
    private String userId;
}
