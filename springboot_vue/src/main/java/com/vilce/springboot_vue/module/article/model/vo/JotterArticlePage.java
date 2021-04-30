package com.vilce.springboot_vue.module.article.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Description: description
 * @ProjectName: learn
 * @Package: com.vilce.springboot_vue.module.article.model.vo
 * @Author: 雷才哲
 * @Date: 2021/4/29 下午2:37
 * @Version: 1.0
 */
@ApiModel(value = "分页文章返参")
public class JotterArticlePage {
    @ApiModelProperty(value = "文章")
    private List<JotterArticleRes> articleResList;
    @ApiModelProperty(value = "文章总量")
    private int articleNum;

    public List<JotterArticleRes> getArticleResList() {
        return articleResList;
    }

    public void setArticleResList(List<JotterArticleRes> articleResList) {
        this.articleResList = articleResList;
    }

    public int getArticleNum() {
        return articleNum;
    }

    public void setArticleNum(int articleNum) {
        this.articleNum = articleNum;
    }
}
