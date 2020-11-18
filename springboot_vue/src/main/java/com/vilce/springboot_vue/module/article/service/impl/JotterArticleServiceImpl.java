package com.vilce.springboot_vue.module.article.service.impl;

import com.vilce.common.model.enums.ResultStatus;
import com.vilce.common.model.exception.BasicException;
import com.vilce.common.model.po.BaseResponse;
import com.vilce.common.utils.JSONUtils;
import com.vilce.springboot_vue.module.article.mapper.JotterArticleMapper;
import com.vilce.springboot_vue.module.article.model.po.JotterArticle;
import com.vilce.springboot_vue.module.article.model.vo.JotterArticleRes;
import com.vilce.springboot_vue.module.article.service.JotterArticleService;
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
 * @Package: com.vilce.springboot_vue.module.article.service.impl.JotterArticleServiceImpl
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
     * @param pageIndex 页码
     * @param pageSize  每页数量
     * @return
     */
    @Override
    public List<JotterArticleRes> listArticles(int pageIndex, int pageSize) {
        List<JotterArticle> articleList = jotterArticleMapper.findAll((pageIndex - 1) * pageSize, pageIndex * pageSize);
        List<JotterArticleRes> articleResList = new ArrayList<>();
        articleList.forEach(article -> {
            JotterArticleRes articleRes = JotterArticleRes.create(article);
            articleResList.add(articleRes);
        });
        return articleResList;
    }

    /**
     * 根据id获取文章
     *
     * @param id
     * @return
     */
    @Override
    public JotterArticleRes findArticleById(int id) {
        String redisKey = StringUtils.join(ARTICLE, "id:", id);
        String redisStr = redisTemplate.opsForValue().get(redisKey);
        if (StringUtils.isNotEmpty(redisStr)) {
            return JSONUtils.toJavaBean(redisStr, JotterArticleRes.class);
        }
        JotterArticle article = jotterArticleMapper.findArticleById(id);
        JotterArticleRes articleRes = JotterArticleRes.create(article);
        if (ObjectUtils.isNotEmpty(articleRes)) {
            redisTemplate.opsForValue().set(redisKey, JSONUtils.toJSONPrettyString(articleRes), redisTimeOut, TimeUnit.SECONDS);
        }
        return articleRes;
    }

    /**
     * 添加或更新文章信息
     *
     * @param article
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse addOrUpdate(JotterArticle article) {
        BaseResponse baseResponse;
        if (article.getId() != 0) {
            // 文章id不为空，更新文章
            if (jotterArticleMapper.updateArticle(article)) {
                baseResponse = BaseResponse.buildResponse(0, "更新文章成功！");
            } else {
                throw new BasicException(ResultStatus.ERROR.getStatus(), "更新文章失败!");
            }
        } else {
            // 如果文章id为空，此时添加文章
            if (jotterArticleMapper.addArticle(article)) {
                baseResponse = BaseResponse.buildResponse(0, "添加文章成功！");
            } else {
                throw new BasicException(ResultStatus.ERROR.getStatus(), "添加文章失败!");
            }
        }
        // 添加或更新成功后，也同步添加或更新缓存
        String redisKey = StringUtils.join(ARTICLE, article.getId());
        redisTemplate.opsForValue().set(redisKey, JSONUtils.toJSONPrettyString(article), redisTimeOut, TimeUnit.SECONDS);
        return baseResponse;
    }

    /**
     * 根据id删除文章
     *
     * @param id
     */
    @Override
    public BaseResponse deleteArticle(int id) {
        if (jotterArticleMapper.deleteArticleById(id)) {
            // 删除当前文章缓存
            redisTemplate.opsForValue().decrement(StringUtils.join(ARTICLE, "id:", id));
            return BaseResponse.buildResponse(0, "删除文章成功！");
        } else {
            throw new BasicException(ResultStatus.ERROR.getStatus(), "删除文章失败!");
        }
    }

    /**
     * 统计文章数量
     *
     * @return
     */
    @Override
    public Integer countArticle() {
        return jotterArticleMapper.countArticle();
    }
}
