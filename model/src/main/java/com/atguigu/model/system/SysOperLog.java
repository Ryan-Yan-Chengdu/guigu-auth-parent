package com.atguigu.model.system;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "SysOperLog")
@TableName("sys_oer_log")
public class SysOperLog {
    private static final long serialVersionUID=1L;

    @TableField("title")
    @ApiModelProperty(value = "模块标题")
    private String title;

    @TableField("business_type")
    @ApiModelProperty(value = "业务类型(0其他 1新增 2 修改 3删除)")
    private String businessType;

    @TableField("method")
    @ApiModelProperty(value = "方法名称")
    private String method;

    @TableField("request_method")
    @ApiModelProperty(value = "请求方式")
    private String requestMethod;

    @TableField("operator_type")
    @ApiModelProperty(value = "操作类型: (0 其它 1后台用户 2手机端用户")
    private String operatorType;

    @TableField("oper_name")
    @ApiModelProperty(value = "操作人员")
    private String operName;

    @TableField("dept_name")
    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @TableField("oper_url")
    @ApiModelProperty(value = "请求url")
    private String operUrl;

    @TableField("oper_id")
    @ApiModelProperty(value = "主机地址")
    private String operIp;

    @TableField("oper_param")
    @ApiModelProperty(value = "请求参数")
    private String operParam;

    @TableField("json_result")
    @ApiModelProperty(value = "返回参数")
    private String jsonResult;

    @TableField("status")
    @ApiModelProperty(value = "操作状态（0正常 1异常)")
    private Integer status;

    @TableField("error_msg")
    @ApiModelProperty(value = "错误消息")
    private String errorMsg;

    @TableField("oper_time")
    @ApiModelProperty(value = "操作时间")
    private Date operTime;


}
