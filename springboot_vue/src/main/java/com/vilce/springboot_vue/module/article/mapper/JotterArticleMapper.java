package com.vilce.springboot_vue.module.article.mapper;

import com.vilce.springboot_vue.module.article.model.po.JotterArticle;
import com.vilce.springboot_vue.module.article.model.vo.ArticleLabel;
import com.vilce.springboot_vue.module.article.model.vo.ArticleType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 文章相关数据Mapper
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.article.mapper.JotterArticleMapper
 * @Author: 雷才哲
 * @Date: 2020/8/25 15:31
 * @Version: 1.0
 */
@Mapper
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
    List<JotterArticle> findAll(int page, int size);

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

    /**
     * 获取某类文章
     *
     * @param type 类别
     * @return
     */
    List<JotterArticle> getArticleByType(String type);

    /**
     * 获取某标签文章
     *
     * @param label 标签
     * @return
     */
    List<JotterArticle> getArticleByLabel(String label);

    /**
     * 模糊查询文章
     *
     * @param searchStr 查询字符串
     * @return
     */
    List<JotterArticle> searchArticle(String searchStr);

    /**
     * 统计文章数
     *
     * @return
     */
    Integer countArticle();

    /**
     * 统计文章种类
     *
     * @return
     */
    List<ArticleType> statisticsTypes();

    /**
     * 统计文章标签
     *
     * @return
     */
    List<ArticleLabel> statisticsLabels();

    /**
     * 获取全部文章标签
     *
     * @return
     */
    List<String> getAllLabels();

    /**
     * 删除文章所有标签
     *
     * @param articleId 文章id
     */
    boolean deleteArticleLabel(int articleId);

    /**
     * 添加文章标签
     *
     * @param articleId 文章id
     * @param label     标签
     * @return
     */
    boolean addArticleLabel(int articleId, String label);

    /**
     * 获取文章标签
     *
     * @param articleId 文章id
     * @return
     */
    List<String> findArticleLabel(int articleId);

    /**
     * 获取含有标签的所有文章id
     *
     * @param label 标签
     * @return
     */
    List<Integer> getArticleIdByLabel(String label);
}
