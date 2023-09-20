package com.atguigu.model.system;

import com.atguigu.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "岗位")
@TableName("sys_post")
public class SysPost extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("post_code")
    @ApiModelProperty(value = "岗位编码")
    private String postCode;

    @TableField("name")
    @ApiModelProperty(value = "岗位名称")
    private String name;

    @TableField("description")
    @ApiModelProperty(value = "显示顺序")
    private String description;

    @TableField("status")
    @ApiModelProperty(value = "状态 (1正常 0停用)")
    private Integer status;


}
