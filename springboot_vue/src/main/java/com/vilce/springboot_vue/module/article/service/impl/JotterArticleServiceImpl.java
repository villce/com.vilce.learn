package com.vilce.springboot_vue.module.article.service.impl;

import com.google.common.collect.Lists;
import com.vilce.common.model.enums.ResultStatus;
import com.vilce.common.model.exception.BasicException;
import com.vilce.common.model.po.BaseResponse;
import com.vilce.common.utils.JSONUtils;
import com.vilce.springboot_vue.module.article.mapper.JotterArticleMapper;
import com.vilce.springboot_vue.module.article.model.po.JotterArticle;
import com.vilce.springboot_vue.module.article.model.vo.ArticleDetails;
import com.vilce.springboot_vue.module.article.model.vo.ArticleStatistic;
import com.vilce.springboot_vue.module.article.model.vo.JotterArticlePage;
import com.vilce.springboot_vue.module.article.model.vo.JotterArticleRes;
import com.vilce.springboot_vue.module.article.service.JotterArticleService;
import com.vilce.springboot_vue.utils.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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

    private static final String ARTICLE = "com:vilce:springbootVue:article:";

    /**
     * 分页获取文章
     *
     * @param pageIndex 页码
     * @param pageSize  每页数量
     * @return
     */
    @Override
    public JotterArticlePage listArticles(int pageIndex, int pageSize) {
        JotterArticlePage jotterArticlePage = new JotterArticlePage();
        List<JotterArticle> articleList = jotterArticleMapper.findAll((pageIndex - 1) * pageSize, pageSize);
        List<JotterArticleRes> articleResList = convertArticleResList(articleList);
        jotterArticlePage.setArticleResList(articleResList);
        jotterArticlePage.setArticleNum(jotterArticleMapper.countArticle());
        return jotterArticlePage;
    }

    /**
     * 根据id获取文章
     *
     * @param id
     * @return
     */
    @Override
    public ArticleDetails findArticleById(int id) {
        JotterArticle article = jotterArticleMapper.findArticleById(id);
        article.setArticle_label(jotterArticleMapper.findArticleLabel(id));
        ArticleDetails articleRes = ArticleDetails.convert(article);
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
        BaseResponse baseResponse = null;
        if (article.getId() != 0) {
            // 文章id不为空，更新文章(先更新文章内容，再更新文章标签，标签更新需要先删除所有文章标签，再插入新的标签)
            if (jotterArticleMapper.updateArticle(article) && jotterArticleMapper.deleteArticleLabel(article.getId())) {
                article.getArticle_label().forEach(label -> {
                    jotterArticleMapper.addArticleLabel(article.getId(), label);
                });
                baseResponse = BaseResponse.buildResponse(0, "更新文章成功！");
            } else {
                throw new BasicException(ResultStatus.ERROR.getStatus(), "更新文章失败!");
            }
        } else {
            // 如果文章id为空，此时添加文章
            if (jotterArticleMapper.addArticle(article)) {
                article.getArticle_label().forEach(label -> {
                    jotterArticleMapper.addArticleLabel(article.getId(), label);
                });
                baseResponse = BaseResponse.buildResponse(0, "添加文章成功！");
            } else {
                throw new BasicException(ResultStatus.ERROR.getStatus(), "添加文章失败!");
            }
        }
        // 删除所有相关缓存
        Set<String> keys = redisTemplate.keys(ARTICLE + "*");
        redisTemplate.delete(keys);
        return baseResponse;
    }

    /**
     * 根据id删除文章
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse deleteArticle(int id) {
        if (jotterArticleMapper.deleteArticleById(id) && jotterArticleMapper.deleteArticleLabel(id)) {
            // 删除所有相关缓存
            Set<String> keys = redisTemplate.keys(ARTICLE + "*");
            redisTemplate.delete(keys);
            return BaseResponse.buildResponse(0, "删除文章成功！");
        } else {
            throw new BasicException(ResultStatus.ERROR.getStatus(), "删除文章失败!");
        }
    }

    /**
     * 获取某类文章
     *
     * @param type 类别
     * @return
     */
    @Override
    public JotterArticlePage getArticleByType(int pageIndex, int pageSize, String type) {
        JotterArticlePage jotterArticlePage = new JotterArticlePage();
        List<JotterArticle> articleList = jotterArticleMapper.getArticleByType((pageIndex - 1) * pageSize, pageSize, type);
        List<JotterArticleRes> articleResList = convertArticleResList(articleList);
        jotterArticlePage.setArticleResList(articleResList);
        jotterArticlePage.setArticleNum(jotterArticleMapper.countArticleByType(type));
        return jotterArticlePage;
    }

    /**
     * 获取某标签文章
     *
     * @param label 标签
     * @return
     */
    @Override
    public JotterArticlePage getArticleByLabel(int pageIndex, int pageSize, String label) {
        JotterArticlePage jotterArticlePage = new JotterArticlePage();
        List<JotterArticleRes> articleResList = Lists.newLinkedList();
        String redisKey = StringUtils.join(ARTICLE, label);
        String redisStr = redisTemplate.opsForValue().get(redisKey);
        if (StringUtils.isNotBlank(redisStr)) {
            articleResList = JSONUtils.toJavaBean(redisStr, List.class, JotterArticleRes.class);
        } else {
            List<Integer> articleIdList = jotterArticleMapper.getArticleIdByLabel(label);
            for (Integer articleId : articleIdList) {
                JotterArticle article = jotterArticleMapper.findArticleById(articleId);
                article.setArticle_label(jotterArticleMapper.findArticleLabel(articleId));
                articleResList.add(JotterArticleRes.create(article));
            }
            Collections.sort(articleResList);
            redisTemplate.opsForValue().set(ARTICLE, JSONUtils.toJSONString(articleResList), 300, TimeUnit.SECONDS);
        }
        jotterArticlePage.setArticleResList(PageUtil.startPage(articleResList, pageIndex, pageSize));
        jotterArticlePage.setArticleNum(articleResList.size());
        return jotterArticlePage;
    }

    /**
     * 模糊查询文章
     *
     * @param searchStr 查询字符串
     * @return
     */
    @Override
    public JotterArticlePage searchArticle(int pageIndex, int pageSize, String searchStr) {
        JotterArticlePage jotterArticlePage = new JotterArticlePage();
        List<JotterArticle> articleList = jotterArticleMapper.searchArticle((pageIndex - 1) * pageSize, pageSize,
                StringUtils.join("%", searchStr, "%"));
        List<JotterArticleRes> articleResList = convertArticleResList(articleList);
        jotterArticlePage.setArticleResList(articleResList);
        jotterArticlePage.setArticleNum(jotterArticleMapper.countArticleByStr(searchStr));
        return jotterArticlePage;
    }

    /**
     * 统计文章
     *
     * @return
     */
    @Override
    public ArticleStatistic statisticsArticle() {
        ArticleStatistic articleStatistic = new ArticleStatistic();
        articleStatistic.setArticleNum(jotterArticleMapper.countArticle());
        articleStatistic.setArticleTypes(jotterArticleMapper.statisticsTypes());
        articleStatistic.setTypeNum(articleStatistic.getArticleTypes().size());
        articleStatistic.setArticleLabels(jotterArticleMapper.statisticsLabels());
        articleStatistic.setLabelNum(articleStatistic.getArticleLabels().size());
        return articleStatistic;
    }

    /**
     * 转换数据库中的返回对象
     *
     * @param articleList 数据库返回文章队列
     * @return
     */
    public List<JotterArticleRes> convertArticleResList(List<JotterArticle> articleList) {
        List<JotterArticleRes> articleResList = new ArrayList<>();
        articleList.forEach(article -> {
            article.setArticle_label(jotterArticleMapper.findArticleLabel(article.getId()));
            JotterArticleRes articleRes = JotterArticleRes.create(article);
            articleResList.add(articleRes);
        });
        return articleResList;
    }
}
