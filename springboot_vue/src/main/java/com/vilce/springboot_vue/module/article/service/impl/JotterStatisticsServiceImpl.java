package com.vilce.springboot_vue.module.article.service.impl;

import com.vilce.springboot_vue.module.article.mapper.JotterStatisticsMapper;
import com.vilce.springboot_vue.module.article.model.po.ArticleLabel;
import com.vilce.springboot_vue.module.article.service.JotterStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.article.service.impl.JotterStatisticsServiceImpl
 * @Author: 雷才哲
 * @Date: 2020/11/18 14:07
 * @Version: 1.0
 */
@Service
public class JotterStatisticsServiceImpl implements JotterStatisticsService {

    @Autowired
    private JotterStatisticsMapper jotterStatisticsMapper;

    @Override
    public List<ArticleLabel> statisticsLabels() {
        return jotterStatisticsMapper.statisticsLabels();
    }
}
