package com.vilce.springboot_vue.module.article.service;

import com.vilce.common.model.po.BaseResponse;
import com.vilce.springboot_vue.module.article.model.po.JotterArticle;
import com.vilce.springboot_vue.module.article.model.vo.JotterArticleRes;

import java.util.List;

/**
 * @Description: 文章相关服务
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.article.service.JotterArticleService
 * @Author: 雷才哲
 * @Date: 2020/8/25 15:37
 * @Version: 1.0
 */
public interface JotterArticleService {
    /**
     * 添加或更新文章信息
     *
     * @param article
     * @return
     */
    BaseResponse addOrUpdate(JotterArticle article);

    /**
     * 分页获取文章信息
     *
     * @param pageIndex 页码
     * @param pageSize  每页数量
     * @return
     */
    List<JotterArticleRes> listArticles(int pageIndex, int pageSize);

    /**
     * 根据id获取文章信息
     *
     * @param id
     * @return
     */
    JotterArticleRes findArticleById(int id);

    /**
     * 根据id删除文章
     *
     * @param id
     * @return
     */
    BaseResponse deleteArticle(int id);

    /**
     * 统计文章数量
     *
     * @return
     */
    Integer countArticle();
}
