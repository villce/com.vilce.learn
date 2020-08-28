package com.vilce.springboot_vue.service;

import com.vilce.common.model.po.BaseResponse;
import com.vilce.springboot_vue.model.po.JotterArticle;
import com.vilce.springboot_vue.model.vo.respones.JotterArticleRes;

import java.util.List;

/**
 * @Description: 文章相关服务
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.JotterArticleService
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
     * @param page
     * @param size
     * @return
     */
    List<JotterArticleRes> listArticles(int page, int size);

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
}
