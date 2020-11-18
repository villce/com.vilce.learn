package com.vilce.springboot_vue.module.wage.service.impl;

import com.vilce.common.model.enums.ResultStatus;
import com.vilce.common.model.exception.BasicException;
import com.vilce.common.model.po.BaseResponse;
import com.vilce.springboot_vue.module.wage.mapper.WageMapper;
import com.vilce.springboot_vue.module.wage.model.po.WageOrder;
import com.vilce.springboot_vue.module.wage.model.vo.WageOrderRes;
import com.vilce.springboot_vue.module.wage.service.WageService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 工资相关服务实现
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.wage.service.impl.WageServiceImpl
 * @Author: 雷才哲
 * @Date: 2019/12/20 9:51
 * @Version: 1.0
 */
@Service
public class WageServiceImpl implements WageService {

    @Autowired
    private WageMapper wageMapper;

    /**
     * 获取所有工资条信息
     *
     * @return
     */
    @Override
    public List<WageOrderRes> getAllWageOrder() {
        List<WageOrder> wageOrderList = wageMapper.getAllWageOrder();
        List<WageOrderRes> wageOrderResList = new ArrayList<>();
        for (WageOrder wageOrder : wageOrderList) {
            WageOrderRes wageOrderRes = WageOrderRes.create(wageOrder);
            wageOrderResList.add(wageOrderRes);
        }
        return wageOrderResList;
    }

    /**
     * 添加或更新工资条信息
     *
     * @param wageOrder
     * @return
     */
    @Override
    public BaseResponse addOrUpdateWageOrder(WageOrder wageOrder) {
        if (ObjectUtils.isEmpty(wageOrder.getId()) || wageOrder.getId() == 0) {
            if (wageMapper.addWageOrder(wageOrder)) {
                return BaseResponse.buildResponse(ResultStatus.OK.getStatus(), "添加工资条信息成功！");
            } else {
                throw new BasicException(ResultStatus.ERROR.getStatus(), "添加工资条信息失败！");
            }
        } else {
            if (wageMapper.updateWageOrder(wageOrder)) {
                return BaseResponse.buildResponse(ResultStatus.OK.getStatus(), "更新工资条信息成功！");
            } else {
                throw new BasicException(ResultStatus.ERROR.getStatus(), "更新工资条信息失败！");
            }
        }
    }

    /**
     * 删除工资条信息
     *
     * @param id
     * @return
     */
    @Override
    public BaseResponse deleteWageOrder(int id) {
        if (wageMapper.deleteWageOrder(id)) {
            return BaseResponse.buildResponse(ResultStatus.OK.getStatus(), "删除工资条信息成功！");
        } else {
            throw new BasicException(ResultStatus.ERROR.getStatus(), "删除工资条信息失败！");
        }
    }
}
