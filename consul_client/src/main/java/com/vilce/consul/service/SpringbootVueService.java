package com.vilce.consul.service;

import com.vilce.common.model.po.BaseResponse;
import com.vilce.consul.config.feign.FeignConfiguration;
import com.vilce.consul.model.po.JotterArticleRes;
import com.vilce.consul.model.po.WageOrderRes;
//import com.vilce.consul.service.impl.SpringbootVueServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.consul.service.SpringbootVueService
 * @Author: 雷才哲
 * @Date: 2020/12/3 16:32
 * @Version: 1.0
 */
@FeignClient(value = "Vilce-SpringBoot-Vue", configuration = FeignConfiguration.class)
public interface SpringbootVueService {
    @GetMapping("/api/wage/getAllWageOrder")
    BaseResponse<List<WageOrderRes>> getAllWageOrder();

    @GetMapping("/api/article/listArticles/{pageIndex}/{pageSize}")
    BaseResponse<List<JotterArticleRes>> listArticles(@PathVariable int pageIndex,@PathVariable int pageSize);
}
