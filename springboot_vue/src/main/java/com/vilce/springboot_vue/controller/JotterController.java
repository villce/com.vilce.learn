package com.vilce.springboot_vue.controller;

import com.vilce.springboot_vue.model.po.JotterArticle;
import com.vilce.springboot_vue.service.JotterArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description: 文章相关API
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.controller.JotterController
 * @Author: 雷才哲
 * @Date: 2020/8/26 16:36
 * @Version: 1.0
 */
@RestController
@RequestMapping("/article")
@Api(tags = "文章相关API")
public class JotterController {

    @Autowired
    private JotterArticleService jotterArticleService;

    //    @PostMapping("api/admin/content/article")
    @ApiOperation(value = "添加或更新文章信息")
    @PostMapping("saveArticle")
    public boolean saveArticle(@RequestBody @Valid JotterArticle article) {
        return jotterArticleService.addOrUpdate(article);
    }

    //    @GetMapping("/api/article/{size}/{page}")
    @GetMapping("listArticles")
    @ApiOperation(value = "分页展示文章信息")
    public List<JotterArticle> listArticles(int page, int size) {
        return jotterArticleService.listArticles(page, size);
    }

    //    @GetMapping("/api/article/{id}")
    @GetMapping("getOneArticle")
    @ApiOperation(value = "根据id获取文章")
    public JotterArticle getOneArticle(int id) {
        return jotterArticleService.findArticleById(id);
    }

    //    @DeleteMapping("/api/admin/content/article/{id}")
    @GetMapping("deleteArticle")
    @ApiOperation(value = "根据id删除文章")
    public boolean deleteArticle(int id) {
        return jotterArticleService.deleteArticle(id);
    }
}
