package com.vilce.springboot_vue.service.Impl;

import com.vilce.common.utils.JSONUtils;
import com.vilce.springboot_vue.config.redis.RedisService;
import com.vilce.springboot_vue.mapper.JotterArticleMapper;
import com.vilce.springboot_vue.model.po.JotterArticle;
import com.vilce.springboot_vue.service.JotterArticleService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    private RedisService redisService;

    private static final String ARTICLE = "com.vilce.springbootVue.article:";

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
        String key = StringUtils.join(ARTICLE, "size:", size);
        // 只缓存第一页
        if (page == 1) {
            // todo 优化redis
            articleList = (List<JotterArticle>) redisService.get(key);
            if (articleList.isEmpty()) {
                articleList = jotterArticleMapper.findAll(page - 1, size);
                redisService.set(key, articleList);
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
        String key = StringUtils.join(ARTICLE, "id:", id);
        // todo 优化redis
        JotterArticle article = (JotterArticle) redisService.get(key);
        System.out.println(ObjectUtils.isEmpty(article));
        if (ObjectUtils.isEmpty(article)) {
            article = jotterArticleMapper.findArticleById(id);
            System.out.println(JSONUtils.toJson(article));
            redisService.set(key, article);
        }
        return article;
    }

    /**
     * 添加或更新文章信息
     *
     * @param article
     */
    @Override
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
            String url = StringUtils.join(ARTICLE, article.getId());
            // todo 缓存优化
            redisService.set(url, article);
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
            // todo 缓存优化
            redisService.delete(StringUtils.join(ARTICLE, "id:", id));
        }
        return result;
    }

}
