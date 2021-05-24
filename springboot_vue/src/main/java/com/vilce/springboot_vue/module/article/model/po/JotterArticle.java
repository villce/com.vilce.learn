package com.vilce.springboot_vue.module.article.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @Description: 文章
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.article.model.po.JotterArticle
 * @Author: 雷才哲
 * @Date: 2019/12/23 11:21
 * @Version: 1.0
 */
@ApiModel(value = "文章")
public class JotterArticle {
    @ApiModelProperty(value = "文章id",example = "0")
    private int id;
    @ApiModelProperty(value = "文章类型", example = "随笔")
    private String article_type;
    @ApiModelProperty(value = "文章标签", example = "java")
    private List<String> article_label;
    @ApiModelProperty(value = "文章标题",example = "示例")
    private String article_title;
    @ApiModelProperty(value = "文章html",example = "示例")
    private String article_content_html;
    @ApiModelProperty(value = "文章md",example = "示例")
    private String article_content_md;
    @ApiModelProperty(value = "文章发布时间",example = "2020.08.26")
    private Date article_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticle_type() {
        return article_type;
    }

    public void setArticle_type(String article_type) {
        this.article_type = article_type;
    }

    public List<String> getArticle_label() {
        return article_label;
    }

    public void setArticle_label(List<String> article_label) {
        this.article_label = article_label;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_content_html() {
        return article_content_html;
    }

    public void setArticle_content_html(String article_content_html) {
        this.article_content_html = article_content_html;
    }

    public String getArticle_content_md() {
        return article_content_md;
    }

    public void setArticle_content_md(String article_content_md) {
        this.article_content_md = article_content_md;
    }

    public Date getArticle_date() {
        return article_date;
    }

    public void setArticle_date(Date article_date) {
        this.article_date = article_date;
    }
}
