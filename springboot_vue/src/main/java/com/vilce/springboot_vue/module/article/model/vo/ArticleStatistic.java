package com.vilce.springboot_vue.module.article.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.article.model.vo.ArticleStatistic
 * @Author: 雷才哲
 * @Date: 2020/11/20 17:04
 * @Version: 1.0
 */
@ApiModel(value = "文章统计")
public class ArticleStatistic {
    @ApiModelProperty(value = "文章数")
    private Integer articleNum;
    @ApiModelProperty(value = "种类数")
    private Integer typeNum;
    @ApiModelProperty(value = "标签数")
    private Integer labelNum;
    @ApiModelProperty(value = "文章分类统计")
    private List<ArticleType> articleTypes;
    @ApiModelProperty(value = "文章标签统计")
    private List<ArticleLabel> articleLabels;

    public Integer getArticleNum() {
        return articleNum;
    }

    public void setArticleNum(Integer articleNum) {
        this.articleNum = articleNum;
    }

    public Integer getTypeNum() {
        return typeNum;
    }

    public void setTypeNum(Integer typeNum) {
        this.typeNum = typeNum;
    }

    public Integer getLabelNum() {
        return labelNum;
    }

    public void setLabelNum(Integer labelNum) {
        this.labelNum = labelNum;
    }

    public List<ArticleType> getArticleTypes() {
        return articleTypes;
    }

    public void setArticleTypes(List<ArticleType> articleTypes) {
        this.articleTypes = articleTypes;
    }

    public List<ArticleLabel> getArticleLabels() {
        return articleLabels;
    }

    public void setArticleLabels(List<ArticleLabel> articleLabels) {
        this.articleLabels = articleLabels;
    }
}
