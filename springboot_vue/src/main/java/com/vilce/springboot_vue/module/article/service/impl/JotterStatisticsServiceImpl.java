package com.vilce.springboot_vue.module.article.service.impl;

import com.vilce.common.utils.JSONUtils;
import com.vilce.springboot_vue.module.article.mapper.JotterStatisticsMapper;
import com.vilce.springboot_vue.module.article.model.vo.ArticleLabel;
import com.vilce.springboot_vue.module.article.model.vo.ArticleType;
import com.vilce.springboot_vue.module.article.service.JotterStatisticsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    @Value("${spring.vilce.redis.normal.time}")
    private int redisTimeOut;

    /**
     * 统计文章数量
     *
     * @return
     */
    @Override
    public Integer countArticle() {
        String keyStr = StringUtils.join(ARTICLE, "countArticle");
        String cacheStr = redisTemplate.opsForValue().get(keyStr);
        if (StringUtils.isNotEmpty(cacheStr)) {
            return JSONUtils.toJavaBean(cacheStr, Integer.class);
        }
        Integer count = jotterStatisticsMapper.countArticle();
        redisTemplate.opsForValue().set(keyStr, JSONUtils.toJSONString(count), redisTimeOut, TimeUnit.SECONDS);
        return count;
    }

    /**
     * 统计文章类别
     *
     * @return
     */
    @Override
    public List<ArticleType> statisticsTypes() {
        String keyStr = StringUtils.join(ARTICLE, "statisticsTypes");
        String cacheStr = redisTemplate.opsForValue().get(keyStr);
        if (StringUtils.isNotEmpty(cacheStr)) {
            return JSONUtils.toJavaBean(cacheStr, List.class, ArticleType.class);
        }
        List<ArticleType> articleTypeList = jotterStatisticsMapper.statisticsTypes();
        redisTemplate.opsForValue().set(keyStr, JSONUtils.toJSONString(articleTypeList), redisTimeOut, TimeUnit.SECONDS);
        return articleTypeList;
    }

    /**
     * 统计文章标签
     *
     * @return
     */
    @Override
    public List<ArticleLabel> statisticsLabels() {
        String keyStr = StringUtils.join(ARTICLE, "statisticsLabels");
        String cacheStr = redisTemplate.opsForValue().get(keyStr);
        if (StringUtils.isNotEmpty(cacheStr)) {
            return JSONUtils.toJavaBean(cacheStr, List.class, ArticleLabel.class);
        }
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
        List<ArticleLabel> articleLabels = ArticleLabel.statisticsLabels(labelList);
        redisTemplate.opsForValue().set(keyStr, JSONUtils.toJSONString(articleLabels), redisTimeOut, TimeUnit.SECONDS);
        return articleLabels;
    }
}
