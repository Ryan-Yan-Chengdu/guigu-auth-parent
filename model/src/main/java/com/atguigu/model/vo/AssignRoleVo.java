package com.atguigu.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "分配角色")
public class AssignRoleVo {
    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "角色id列表")
    private List<String> roleIdList;
}
