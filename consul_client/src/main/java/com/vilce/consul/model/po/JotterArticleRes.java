package com.vilce.consul.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 文章
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.article.model.po.JotterArticle
 * @Author: 雷才哲
 * @Date: 2019/12/23 11:21
 * @Version: 1.0
 */
@Data
@ApiModel(value = "文章")
public class JotterArticleRes implements Serializable {
    @ApiModelProperty(value = "文章id",example = "0")
    private int id;
    @ApiModelProperty(value = "文章类型", example = "随笔")
    private String type;
    @ApiModelProperty(value = "文章标签", example = "java")
    private List<String> label;
    @ApiModelProperty(value = "文章标题",example = "示例")
    private String title;
    @ApiModelProperty(value = "文章html",example = "示例")
    private String contentHtml;
    @ApiModelProperty(value = "文章md",example = "示例")
    private String contentMd;
    @ApiModelProperty(value = "文章摘要",example = "示例")
    private String introduction;
    @ApiModelProperty(value = "文章发布时间",example = "2020.08.26 00:00:00")
    private String publishDate;
}
