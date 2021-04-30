package com.vilce.springboot_vue.module.article.controller;

import com.vilce.common.autoconfig.redis.annotation.RedisFilter;
import com.vilce.common.model.po.BaseResponse;
import com.vilce.springboot_vue.module.article.model.po.JotterArticle;
import com.vilce.springboot_vue.module.article.model.vo.ArticleStatistic;
import com.vilce.springboot_vue.module.article.model.vo.JotterArticlePage;
import com.vilce.springboot_vue.module.article.model.vo.JotterArticleRes;
import com.vilce.springboot_vue.module.article.service.JotterArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description: 文章相关API
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.article.controller.JotterController
 * @Author: 雷才哲
 * @Date: 2020/8/26 16:36
 * @Version: 1.0
 */
@RestController
@RequestMapping("/article")
@Api(tags = "文章相关API")
public class JotterArticleController {

    @Autowired
    private JotterArticleService jotterArticleService;

    @ApiOperation(value = "添加或更新文章信息")
    @PostMapping("saveArticle")
    public BaseResponse saveArticle(@RequestBody @Valid JotterArticle article) {
        return jotterArticleService.addOrUpdate(article);
    }

    @GetMapping("listArticles/{pageIndex}/{pageSize}")
    @ApiOperation(value = "分页展示文章信息")
    public JotterArticlePage listArticles(@PathVariable int pageIndex, @PathVariable int pageSize) {
        return jotterArticleService.listArticles(pageIndex, pageSize);
    }

    @GetMapping("getOneArticle/{id}")
    @ApiOperation(value = "根据id获取文章")
    public JotterArticleRes getOneArticle(@PathVariable int id) {
        return jotterArticleService.findArticleById(id);
    }

    @GetMapping("deleteArticle/{id}")
    @ApiOperation(value = "根据id删除文章")
    public BaseResponse deleteArticle(@PathVariable int id) {
        return jotterArticleService.deleteArticle(id);
    }

    @GetMapping("getArticleByType/{pageIndex}/{pageSize}")
    @ApiOperation(value = "分页获取某类文章")
    public JotterArticlePage getArticleByType(@PathVariable int pageIndex, @PathVariable int pageSize, String type) {
        return jotterArticleService.getArticleByType(pageIndex, pageSize, type);
    }

    @GetMapping("getArticleByLabel/{pageIndex}/{pageSize}")
    @ApiOperation(value = "分页获取某标签文章")
    public JotterArticlePage getArticleByLabel(@PathVariable int pageIndex, @PathVariable int pageSize, String label) {
        return jotterArticleService.getArticleByLabel(pageIndex, pageSize, label);
    }

    @GetMapping("search/{pageIndex}/{pageSize}")
    @ApiOperation(value = "模糊查询文章")
    public JotterArticlePage searchArticle(@PathVariable int pageIndex, @PathVariable int pageSize, String searchStr) {
        return jotterArticleService.searchArticle(pageIndex, pageSize, searchStr);
    }

    @GetMapping("statistics")
    @ApiOperation(value = "统计文章")
    public ArticleStatistic statisticsArticle() {
        return jotterArticleService.statisticsArticle();
    }
}
