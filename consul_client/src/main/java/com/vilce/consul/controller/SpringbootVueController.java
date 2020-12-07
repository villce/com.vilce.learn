package com.vilce.consul.controller;

import com.vilce.common.model.enums.ResultStatus;
import com.vilce.common.model.exception.BasicException;
import com.vilce.common.model.po.BaseResponse;
import com.vilce.consul.model.po.JotterArticleRes;
import com.vilce.consul.model.po.WageOrderRes;
import com.vilce.consul.service.SpringbootVueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.consul.controller.SpringbootVueController
 * @Author: 雷才哲
 * @Date: 2020/12/3 16:34
 * @Version: 1.0
 */
@RestController
@RequestMapping("/springbootVue")
public class SpringbootVueController {

    @Autowired
    private SpringbootVueService springbootVueService;

    @GetMapping("getAllWageOrder")
    public List<WageOrderRes> hello() {
        BaseResponse<List<WageOrderRes>> response = springbootVueService.getAllWageOrder();
        return response.getData();
    }

    @GetMapping("listArticles/{pageIndex}/{pageSize}")
    public List<JotterArticleRes> listArticles(@PathVariable int pageIndex,@PathVariable int pageSize) {
        BaseResponse<List<JotterArticleRes>> response = springbootVueService.listArticles(pageIndex, pageSize);
        if (response.getStatus() == ResultStatus.OK.getStatus()) {
            return response.getData();
        }
        throw new BasicException(ResultStatus.API500_EXCEPTION.getStatus(), "获取文章列表异常");
    }
}
