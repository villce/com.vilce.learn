package com.vilce.springboot_vue.module.wage.mapper;

import com.vilce.springboot_vue.module.wage.model.po.WageOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 工资相关数据Mapper
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.wage.mapper.WageMapper
 * @Author: 雷才哲
 * @Date: 2019/12/20 9:52
 * @Version: 1.0
 */
@Mapper
public interface WageMapper {

    /**
     * 获取所有工资信息
     *
     * @return
     */
    List<WageOrder> getAllWageOrder();

    /**
     * 添加工资信息
     *
     * @param wageOrder
     * @return
     */
    boolean addWageOrder(WageOrder wageOrder);

    /**
     * 更新工资信息
     *
     * @param wageOrder
     * @return
     */
    boolean updateWageOrder(WageOrder wageOrder);

    /**
     * 删除工资信息
     *
     * @param id
     * @return
     */
    boolean deleteWageOrder(int id);
}
