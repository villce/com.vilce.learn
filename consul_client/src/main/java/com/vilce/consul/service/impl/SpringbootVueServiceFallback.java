package com.vilce.consul.service.impl;

import com.vilce.common.model.enums.ResultStatus;
import com.vilce.common.model.po.BaseResponse;
import com.vilce.consul.model.po.JotterArticleRes;
import com.vilce.consul.model.po.WageOrderRes;
import com.vilce.consul.service.SpringbootVueService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.consul.service.impl.SpringbootVueServiceFallback
 * @Author: 雷才哲
 * @Date: 2021/1/8 15:25
 * @Version: 1.0
 */
@Component
public class SpringbootVueServiceFallback implements SpringbootVueService {

    @Override
    public BaseResponse<List<WageOrderRes>> getAllWageOrder() {
        BaseResponse response = new BaseResponse();
        response.setMessage("获取失败，降级返回");
        response.setStatus(ResultStatus.OK.getStatus());
        return response;
    }

    @Override
    public BaseResponse<List<JotterArticleRes>> listArticles(int pageIndex, int pageSize) {
        BaseResponse response = new BaseResponse();
        response.setMessage("获取失败，降级返回");
        response.setStatus(ResultStatus.OK.getStatus());
        return response;
    }
}
