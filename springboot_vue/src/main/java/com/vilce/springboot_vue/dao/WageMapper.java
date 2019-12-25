package com.vilce.springboot_vue.dao;

import com.vilce.springboot_vue.model.po.WageOrder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.dao.WageMapper
 * @Author: 雷才哲
 * @Date: 2019/12/20 9:52
 * @Version: 1.0
 */
@Component
public interface WageMapper {

    List<WageOrder> getAllWageOrder();

    boolean addWageOrder(WageOrder wageOrder);

    boolean updateWageOrder(WageOrder wageOrder);

    boolean deleteWageOrder(Long eid);
}
