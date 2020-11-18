package com.vilce.springboot_vue.module.article.mapper;

import com.vilce.springboot_vue.module.article.model.po.ArticleLabel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.article.mapper.JotterStatisticsMapper
 * @Author: 雷才哲
 * @Date: 2020/11/18 14:08
 * @Version: 1.0
 */
@Mapper
public interface JotterStatisticsMapper {

    /**
     * 统计文章标签
     *
     * @return
     */
    List<ArticleLabel> statisticsLabels();
}
