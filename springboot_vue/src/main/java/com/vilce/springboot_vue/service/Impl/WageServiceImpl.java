package com.vilce.springboot_vue.service.Impl;

import com.vilce.common.model.enums.ResultStatus;
import com.vilce.common.model.exception.BasicException;
import com.vilce.common.model.po.BaseResponse;
import com.vilce.springboot_vue.mapper.WageMapper;
import com.vilce.springboot_vue.model.po.WageOrder;
import com.vilce.springboot_vue.model.vo.respones.WageOrderRes;
import com.vilce.springboot_vue.service.WageService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 工资相关服务实现
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
                return BaseResponse.buildResponse(ResultStatus.SUCCESS.getStatus(), "添加工资条信息成功！");
            } else {
                throw new BasicException(ResultStatus.FAIL.getStatus(), "添加工资条信息失败！");
            }
        } else {
            if (wageMapper.updateWageOrder(wageOrder)) {
                return BaseResponse.buildResponse(ResultStatus.SUCCESS.getStatus(), "更新工资条信息成功！");
            } else {
                throw new BasicException(ResultStatus.FAIL.getStatus(), "更新工资条信息失败！");
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
            return BaseResponse.buildResponse(ResultStatus.SUCCESS.getStatus(), "删除工资条信息成功！");
        } else {
            throw new BasicException(ResultStatus.FAIL.getStatus(), "删除工资条信息失败！");
        }
    }
}
