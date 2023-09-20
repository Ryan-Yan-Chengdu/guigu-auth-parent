package com.atguigu.model.system;

import com.atguigu.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description="SysLoginLog")
@TableName("sys_login_log")
public class SysLoginLog extends BaseEntity {
    private static final long serialVersionUID=1L;

    @TableField("username")
    @ApiModelProperty(value = "用户账号")
    private String username;

    @TableField("ipaddr")
    @ApiModelProperty(value = "登录IP地址")
    private String ipaddr;

    @TableField("status")
    @ApiModelProperty(value = "登录状态（0成功，1失败）")
    private Integer status;

    @TableField("msg")
    @ApiModelProperty(value = "提示信息")
    private String msg;

    @TableField("access_time")
    @ApiModelProperty(value = "访问时间")
    private Date accessTime;
}
