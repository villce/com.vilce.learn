package com.vilce.springboot_vue.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 图书分类
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.model.po.Category
 * @Author: 雷才哲
 * @Date: 2019/12/23 11:21
 * @Version: 1.0
 */
@Data
@ApiModel(value = "图书分类")
public class Category {
    @ApiModelProperty(value = "分类id",example = "0")
    private int id;
    @ApiModelProperty(value = "分类名",example = "科技")
    private String name;
}
