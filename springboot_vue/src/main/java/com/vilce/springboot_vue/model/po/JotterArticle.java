package com.vilce.springboot_vue.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 文章
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.model.po.JotterArticle
 * @Author: 雷才哲
 * @Date: 2019/12/23 11:21
 * @Version: 1.0
 */
@Data
@ApiModel(value = "文章")
public class JotterArticle {
    @ApiModelProperty(value = "文章id",example = "0")
    private int id;
    @ApiModelProperty(value = "文章标题",example = "示例")
    private String article_title;
    @ApiModelProperty(value = "文章html",example = "示例")
    private String article_content_html;
    @ApiModelProperty(value = "文章md",example = "示例")
    private String article_content_md;
    @ApiModelProperty(value = "文章摘要",example = "示例")
    private String article_abstract;
    @ApiModelProperty(value = "文章封面地址",example = "d:/img/1.png")
    private String article_cover;
    @ApiModelProperty(value = "文章发布时间",example = "2020.08.26")
    private Date article_date;
}