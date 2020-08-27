package com.vilce.springboot_vue.service.Impl;

import com.vilce.common.utils.JSONUtils;
import com.vilce.springboot_vue.mapper.JotterArticleMapper;
import com.vilce.springboot_vue.model.po.JotterArticle;
import com.vilce.springboot_vue.service.JotterArticleService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 文章相关服务实现
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.Impl.JotterArticleServiceImpl
 * @Author: 雷才哲
 * @Date: 2020/8/25 15:38
 * @Version: 1.0
 */
@Service
public class JotterArticleServiceImpl implements JotterArticleService {

    @Autowired
    private JotterArticleMapper jotterArticleMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String ARTICLE = "com.vilce.springbootVue.article:";
    private static final int redisTimeOut = 300;

    /**
     * 分页获取文章
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<JotterArticle> listArticles(int page, int size) {
        List<JotterArticle> articleList = new ArrayList<>();
        String redisKey = StringUtils.join(ARTICLE, "size:", size);
        System.out.println(redisKey);
        // 只缓存第一页
        if (page == 1) {
            String redisStr = redisTemplate.opsForValue().get(redisKey);
            if (StringUtils.isEmpty(redisStr)) {
                articleList = jotterArticleMapper.findAll(page - 1, size);
                redisTemplate.opsForValue().set(redisKey, JSONUtils.toJsonPretty(articleList), redisTimeOut, TimeUnit.SECONDS);
            }else {
                articleList = JSONUtils.toJavaBean(redisStr, List.class, JotterArticle.class);
            }
        } else {
            articleList = jotterArticleMapper.findAll(page - 1, size);
        }
        return articleList;
    }

    /**
     * 根据id获取文章
     *
     * @param id
     * @return
     */
    @Override
    public JotterArticle findArticleById(int id) {
        String redisKey = StringUtils.join(ARTICLE, "id:", id);
        String redisStr = redisTemplate.opsForValue().get(redisKey);
        System.out.println(redisStr);
        if (StringUtils.isNotEmpty(redisStr)) {
            return JSONUtils.toJavaBean(redisStr, JotterArticle.class);
        }
        JotterArticle article = jotterArticleMapper.findArticleById(id);
        redisTemplate.opsForValue().set(redisKey, JSONUtils.toJsonPretty(article), redisTimeOut, TimeUnit.SECONDS);
        return article;
    }

    /**
     * 添加或更新文章信息
     *
     * @param article
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addOrUpdate(JotterArticle article) {
        boolean result = false;
        if (ObjectUtils.isEmpty(article.getId())) {
            // 如果文章id为空，此时添加文章
            result = jotterArticleMapper.addArticle(article);
        } else {
            // 文章id不为空，更新文章
            result = jotterArticleMapper.updateArticle(article);
        }
        // 添加或更新成功后，也同步添加或更新缓存
        if (result) {
            String redisKey = StringUtils.join(ARTICLE, article.getId());
            redisTemplate.opsForValue().set(redisKey, JSONUtils.toJsonPretty(article), redisTimeOut, TimeUnit.SECONDS);
        }
        return result;
    }

    /**
     * 根据id删除文章
     *
     * @param id
     */
    @Override
    public boolean deleteArticle(int id) {
        boolean result = jotterArticleMapper.deleteArticleById(id);
        if (result) {
            redisTemplate.opsForValue().decrement(StringUtils.join(ARTICLE, "id:", id));
        }
        return result;
    }

}
