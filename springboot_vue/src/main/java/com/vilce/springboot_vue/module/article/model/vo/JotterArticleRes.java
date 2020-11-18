package com.vilce.springboot_vue.module.article.model.vo;

import com.vilce.common.utils.TimeUtils;
import com.vilce.springboot_vue.module.article.model.po.JotterArticle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

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
public class JotterArticleRes {
    @ApiModelProperty(value = "文章id",example = "0")
    private int id;
    @ApiModelProperty(value = "文章类型", example = "随笔")
    private String type;
    @ApiModelProperty(value = "文章标签", example = "java")
    private String[] label;
    @ApiModelProperty(value = "文章标题",example = "示例")
    private String title;
    @ApiModelProperty(value = "文章html",example = "示例")
    private String contentHtml;
    @ApiModelProperty(value = "文章md",example = "示例")
    private String contentMd;
    @ApiModelProperty(value = "文章摘要",example = "示例")
    private String introduction;
    @ApiModelProperty(value = "文章封面地址",example = "d:/img/1.png")
    private String cover;
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
        jotterArticleRes.setLabel(article.getArticle_label().split(";"));
        jotterArticleRes.setTitle(article.getArticle_title());
        jotterArticleRes.setContentHtml(article.getArticle_content_html());
        jotterArticleRes.setContentMd(article.getArticle_content_md());
        jotterArticleRes.setIntroduction(article.getArticle_abstract());
        jotterArticleRes.setCover(article.getArticle_cover());
        jotterArticleRes.setPublishDate(TimeUtils.dateToYMD(article.getArticle_date()));
        return jotterArticleRes;
    }
}
