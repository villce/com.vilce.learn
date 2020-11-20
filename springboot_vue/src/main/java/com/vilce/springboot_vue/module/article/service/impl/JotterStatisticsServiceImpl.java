package com.vilce.springboot_vue.module.article.service.impl;

import com.vilce.springboot_vue.module.article.mapper.JotterStatisticsMapper;
import com.vilce.springboot_vue.module.article.model.vo.ArticleLabel;
import com.vilce.springboot_vue.module.article.model.vo.ArticleType;
import com.vilce.springboot_vue.module.article.service.JotterStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
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
    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String ARTICLE = "com.vilce.springbootVue.article:";

    /**
     * 统计文章数量
     *
     * @return
     */
    @Override
    public Integer countArticle() {
        return jotterStatisticsMapper.countArticle();
    }

    /**
     * 统计文章类别
     *
     * @return
     */
    @Override
    public List<ArticleType> statisticsTypes() {
        return jotterStatisticsMapper.statisticsTypes();
    }

    /**
     * 统计文章标签
     *
     * @return
     */
    @Override
    public List<ArticleLabel> statisticsLabels() {
        // 获取所有的标签
        List<String> labels = jotterStatisticsMapper.getAllLabels();
        // 将标签进行拆分
        List<String> labelList = new LinkedList<>();
        labels.forEach(label -> {
            String[] strs = label.split(";");
            for (int i = 0; i < strs.length; i++) {
                labelList.add(strs[i]);
            }
        });
        return ArticleLabel.statisticsLabels(labelList);
    }
}
