package com.vilce.springboot_vue.module.article.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.article.model.vo.ArticleStatistic
 * @Author: 雷才哲
 * @Date: 2020/11/20 17:04
 * @Version: 1.0
 */
@Data
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
}
