package com.vilce.springboot_vue.model.po;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
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
public class JotterArticle implements Serializable {
    @ApiModelProperty(value = "文章id",example = "0")
    private int id;
    @ApiModelProperty(value = "文章标题",example = "示例")
    @JsonProperty("article_title")
    private String articleTitle;
    @ApiModelProperty(value = "文章html",example = "示例")
    @JsonProperty("article_content_html")
    private String articleContentHtml;
    @ApiModelProperty(value = "文章md",example = "示例")
    @JsonProperty("article_content_md")
    private String articleContentMd;
    @ApiModelProperty(value = "文章摘要",example = "示例")
    @JsonProperty("article_abstract")
    private String articleAbstract;
    @ApiModelProperty(value = "文章封面地址",example = "d:/img/1.png")
    @JsonProperty("article_cover")
    private String articleCover;
    @ApiModelProperty(value = "文章发布时间",example = "2020.08.26")
    @JsonProperty("article_date")
    private Date articleDate;
}
