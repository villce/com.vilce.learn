package com.vilce.springboot_vue.module.article.model.vo;

import com.vilce.common.utils.RegexUtils;
import com.vilce.common.utils.TimeUtils;
import com.vilce.springboot_vue.module.article.model.po.JotterArticle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.ObjectUtils;

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
@ApiModel(value = "文章")
public class JotterArticleRes implements Serializable,Comparable<JotterArticleRes> {
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

    /**
     * JotterArticle映射数据
     * @param article
     * @return
     */
    public static JotterArticleRes create(JotterArticle article) {
        if (ObjectUtils.isEmpty(article)) {
            return null;
        }
        JotterArticleRes jotterArticleRes = new JotterArticleRes();
        jotterArticleRes.setId(article.getId());
        jotterArticleRes.setType(article.getArticle_type());
        jotterArticleRes.setLabel(article.getArticle_label());
        jotterArticleRes.setTitle(article.getArticle_title());
        jotterArticleRes.setContentHtml(article.getArticle_content_html());
        jotterArticleRes.setContentMd(article.getArticle_content_md().replaceAll("<more>", ""));
        // 文章摘要需要自行截取，取前标识<more>之前的为摘要
        String regex = "(.*?)<more>";
        jotterArticleRes.setIntroduction(RegexUtils.getSubUtilSimple(article.getArticle_content_md(), regex));
        jotterArticleRes.setPublishDate(TimeUtils.dateToYMD(article.getArticle_date()));
        return jotterArticleRes;
    }

    /**
     * 排序时使用绑定时间
     *
     * @param res
     * @return
     */
    @Override
    public int compareTo(JotterArticleRes res) {
        // 按照发布时间排序
        if (this.getPublishDate().compareTo(res.getPublishDate()) > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getLabel() {
        return label;
    }

    public void setLabel(List<String> label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public String getContentMd() {
        return contentMd;
    }

    public void setContentMd(String contentMd) {
        this.contentMd = contentMd;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
}
