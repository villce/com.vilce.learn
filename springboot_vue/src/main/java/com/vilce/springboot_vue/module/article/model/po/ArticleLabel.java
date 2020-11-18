package com.vilce.springboot_vue.module.article.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.article.model.po.ArticleLabel
 * @Author: 雷才哲
 * @Date: 2020/11/18 14:02
 * @Version: 1.0
 */
@Data
@ApiModel(value = "笔记标签")
public class ArticleLabel {
    @ApiModelProperty(value = "标签名")
    private String label;
    @ApiModelProperty(value = "文章数")
    private Integer num;
}
