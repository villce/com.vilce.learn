package com.vilce.springboot_vue.mapper;

import com.vilce.springboot_vue.model.po.JotterArticle;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 文章相关数据Mapper
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.mapper.JotterArticleMapper
 * @Author: 雷才哲
 * @Date: 2020/8/25 15:31
 * @Version: 1.0
 */
@Component
public interface JotterArticleMapper {

    /**
     * 根据id获取文章
     *
     * @param id
     * @return
     */
    JotterArticle findArticleById(int id);

    /**
     * 分页获取全部的文章，并按id顺序排列
     *
     * @param page
     * @param size
     * @return
     */
    List<JotterArticle> findAll(int page,int size);

    /**
     * 根据id删除文章
     *
     * @param id
     */
    boolean deleteArticleById(int id);

    /**
     * 添加文章
     *
     * @param article
     * @return
     */
    boolean addArticle(JotterArticle article);

    /**
     * 更新文章信息
     *
     * @param article
     * @return
     */
    boolean updateArticle(JotterArticle article);
}