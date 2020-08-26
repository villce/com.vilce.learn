package com.vilce.springboot_vue.service.Impl;

import com.vilce.springboot_vue.mapper.WageMapper;
import com.vilce.springboot_vue.model.po.WageOrder;
import com.vilce.springboot_vue.model.vo.respones.WageOrderRes;
import com.vilce.springboot_vue.service.WageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.Impl.WageServiceImpl
 * @Author: 雷才哲
 * @Date: 2019/12/20 9:51
 * @Version: 1.0
 */
@Service
public class WageServiceImpl implements WageService {

    @Autowired
    private WageMapper wageMapper;

    @Override
    public List<WageOrderRes> getAllWageOrder() {
        List<WageOrder> wageOrderList = wageMapper.getAllWageOrder();
        List<WageOrderRes> wageOrderResList = new ArrayList<>();
        for (WageOrder wageOrder:wageOrderList){
            WageOrderRes wageOrderRes = WageOrderRes.create(wageOrder);
            wageOrderResList.add(wageOrderRes);
        }
        return wageOrderResList;
    }

    @Override
    public boolean addWageOrder(WageOrder wageOrder) {
        return wageMapper.addWageOrder(wageOrder);
    }

    @Override
    public boolean updateWageOrder(WageOrder wageOrder) {
        return wageMapper.updateWageOrder(wageOrder);
    }

    @Override
    public boolean deleteWageOrder(Long eid) {
        return wageMapper.deleteWageOrder(eid);
    }
}
