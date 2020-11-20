package com.vilce.springboot_vue.module.article.controller;

import com.vilce.common.autoconfig.redis.annotation.RedisFilter;
import com.vilce.springboot_vue.module.article.model.vo.ArticleLabel;
import com.vilce.springboot_vue.module.article.model.vo.ArticleType;
import com.vilce.springboot_vue.module.article.service.JotterStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.article.controller.JotterStatisticsController
 * @Author: 雷才哲
 * @Date: 2020/11/18 13:42
 * @Version: 1.0
 */
@RestController
@Api(tags = "文章统计相关控制器")
@RequestMapping("/article/statistics")
public class JotterStatisticsController {

    @Autowired
    private JotterStatisticsService jotterStatisticsService;

    @RedisFilter
    @GetMapping("countArticle")
    @ApiOperation(value = "统计文章数量")
    public Integer countArticle() {
        return jotterStatisticsService.countArticle();
    }

    @RedisFilter
    @GetMapping("type")
    @ApiOperation(value = "统计文章类别")
    public List<ArticleType> statisticsTypes() {
        return jotterStatisticsService.statisticsTypes();
    }

    @RedisFilter
    @GetMapping("label")
    @ApiOperation(value = "统计文章标签")
    public List<ArticleLabel> statisticsLabels(){
        return jotterStatisticsService.statisticsLabels();
    }
}
