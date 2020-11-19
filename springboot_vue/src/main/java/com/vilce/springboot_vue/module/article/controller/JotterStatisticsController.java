package com.vilce.springboot_vue.module.article.controller;

import com.vilce.springboot_vue.module.article.model.vo.ArticleLabel;
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
@RequestMapping("/jotter/statistics")
public class JotterStatisticsController {

    @Autowired
    private JotterStatisticsService jotterStatisticsService;

    @GetMapping("labels")
    @ApiOperation(value = "统计文章标签")
    public List<ArticleLabel> statisticsLabels(){
        return jotterStatisticsService.statisticsLabels();
    }
}
