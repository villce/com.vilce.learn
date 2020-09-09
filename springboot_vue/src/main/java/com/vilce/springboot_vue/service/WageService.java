package com.vilce.springboot_vue.service;

import com.vilce.common.model.po.BaseResponse;
import com.vilce.springboot_vue.model.po.WageOrder;
import com.vilce.springboot_vue.model.vo.respones.WageOrderRes;

import java.util.List;

/**
 * @Description: 工资相关服务
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.WageService
 * @Author: 雷才哲
 * @Date: 2019/12/20 9:50
 * @Version: 1.0
 */
public interface WageService {

    /**
     * 获取所有工资条信息
     *
     * @return
     */
    List<WageOrderRes> getAllWageOrder();

    /**
     * 添加或更新工资条信息
     *
     * @param wageOrder
     * @return
     */
    BaseResponse addOrUpdateWageOrder(WageOrder wageOrder);

    /**
     * 删除工资条信息
     *
     * @param id
     * @return
     */
    BaseResponse deleteWageOrder(int id);
}
