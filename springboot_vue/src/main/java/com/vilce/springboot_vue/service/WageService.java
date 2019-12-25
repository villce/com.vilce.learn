package com.vilce.springboot_vue.service;

import com.vilce.springboot_vue.model.po.WageOrder;
import com.vilce.springboot_vue.model.vo.respones.WageOrderRes;

import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.WageService
 * @Author: 雷才哲
 * @Date: 2019/12/20 9:50
 * @Version: 1.0
 */
public interface WageService {

    List<WageOrderRes> getAllWageOrder();

    boolean addWageOrder(WageOrder wageOrder);

    boolean updateWageOrder(WageOrder wageOrder);

    boolean deleteWageOrder(Long eid);
}
