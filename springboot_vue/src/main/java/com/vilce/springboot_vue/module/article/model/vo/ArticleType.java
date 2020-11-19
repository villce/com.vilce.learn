package com.vilce.springboot_vue.module.article.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.article.model.vo.ArticleType
 * @Author: 雷才哲
 * @Date: 2020/11/19 11:16
 * @Version: 1.0
 */
@Data
@ApiModel(value = "文章分类")
public class ArticleType {
    @ApiModelProperty(value = "分类名")
    private String type;
    @ApiModelProperty(value = "文章数")
    private Integer num;
}
