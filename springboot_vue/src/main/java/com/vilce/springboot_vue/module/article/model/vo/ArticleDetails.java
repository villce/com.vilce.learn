package com.vilce.springboot_vue.module.article.model.vo;

import com.vilce.common.utils.RegexUtils;
import com.vilce.common.utils.TimeUtils;
import com.vilce.springboot_vue.module.article.model.po.JotterArticle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

/**
 * @Description: description
 * @ProjectName: learn
 * @Package: com.vilce.springboot_vue.module.article.model.vo
 * @Author: 雷才哲
 * @Date: 2021/5/31 下午2:30
 * @Version: 1.0
 */
@ApiModel(value = "文章详情")
public class ArticleDetails {
    @ApiModelProperty(value = "文章id", example = "0")
    private int id;
    @ApiModelProperty(value = "文章类型", example = "随笔")
    private String type;
    @ApiModelProperty(value = "文章标签", example = "java")
    private List<String> label;
    @ApiModelProperty(value = "文章标题", example = "示例")
    private String title;
    @ApiModelProperty(value = "文章html", example = "示例")
    private String contentHtml;
    @ApiModelProperty(value = "文章md", example = "示例")
    private String contentMd;
    @ApiModelProperty(value = "文章发布时间", example = "2020.08.26 00:00:00")
    private String publishDate;

    /**
     * 对象转换
     *
     * @param article
     * @return
     */
    public static ArticleDetails convert(JotterArticle article) {
        if (ObjectUtils.isEmpty(article)) {
            return null;
        }
        ArticleDetails articleDetails = new ArticleDetails();
        articleDetails.setId(article.getId());
        articleDetails.setType(article.getArticle_type());
        articleDetails.setLabel(article.getArticle_label());
        articleDetails.setTitle(article.getArticle_title());
        articleDetails.setContentHtml(article.getArticle_content_html().replaceAll("&lt;!more&gt;", ""));
        articleDetails.setContentMd(article.getArticle_content_md().replaceAll("<!more>", ""));
        articleDetails.setPublishDate(TimeUtils.dateToYMD(article.getArticle_date()));
        return articleDetails;
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

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
}
