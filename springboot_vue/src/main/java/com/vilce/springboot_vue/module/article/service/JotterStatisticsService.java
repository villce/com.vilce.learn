package com.vilce.springboot_vue.module.article.service;

import com.vilce.springboot_vue.module.article.model.vo.ArticleLabel;

import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.article.service.JotterStatisticsService
 * @Author: 雷才哲
 * @Date: 2020/11/18 14:07
 * @Version: 1.0
 */
public interface JotterStatisticsService {

    /**
     * 统计文章标签
     *
     * @return
     */
    List<ArticleLabel> statisticsLabels();
}
