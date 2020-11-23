package com.vilce.springboot_vue.module.article.controller;

import com.vilce.common.autoconfig.redis.annotation.RedisFilter;
import com.vilce.common.model.po.BaseResponse;
import com.vilce.springboot_vue.module.article.model.po.JotterArticle;
import com.vilce.springboot_vue.module.article.model.vo.ArticleStatistic;
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

    @RedisFilter
    @GetMapping("listArticles/{pageIndex}/{pageSize}")
    @ApiOperation(value = "分页展示文章信息")
    public List<JotterArticleRes> listArticles(@PathVariable int pageIndex,@PathVariable int pageSize) {
        return jotterArticleService.listArticles(pageIndex, pageSize);
    }

    @RedisFilter()
    @GetMapping("getOneArticle")
    @ApiOperation(value = "根据id获取文章")
    public JotterArticleRes getOneArticle(int id) {
        return jotterArticleService.findArticleById(id);
    }

    @DeleteMapping("deleteArticle")
    @ApiOperation(value = "根据id删除文章")
    public BaseResponse deleteArticle(int id) {
        return jotterArticleService.deleteArticle(id);
    }

    @RedisFilter
    @GetMapping("getArticleByType")
    @ApiOperation(value = "获取某类文章")
    public List<JotterArticleRes> getArticleByType(String type) {
        return jotterArticleService.getArticleByType(type);
    }

    @RedisFilter
    @GetMapping("getArticleByLabel")
    @ApiOperation(value = "获取某标签文章")
    public List<JotterArticleRes> getArticleByLabel(String label) {
        return jotterArticleService.getArticleByLabel(label);
    }

    @RedisFilter
    @GetMapping("search")
    @ApiOperation(value = "模糊查询文章")
    public List<JotterArticleRes> searchArticle(String searchStr) {
        return jotterArticleService.searchArticle(searchStr);
    }

    @RedisFilter
    @GetMapping("statistics")
    @ApiOperation(value = "统计文章")
    public ArticleStatistic statisticsArticle() {
        return jotterArticleService.statisticsArticle();
    }
}
