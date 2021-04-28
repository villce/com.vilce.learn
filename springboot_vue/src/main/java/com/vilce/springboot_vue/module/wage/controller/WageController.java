package com.vilce.springboot_vue.module.wage.controller;

import com.vilce.common.model.po.BaseResponse;
import com.vilce.springboot_vue.module.wage.model.po.WageOrder;
import com.vilce.springboot_vue.module.wage.model.vo.WageOrderRes;
import com.vilce.springboot_vue.module.wage.service.WageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 工资相关API
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.wage.controller.WageController
 * @Author: 雷才哲
 * @Date: 2019/12/19 20:01
 * @Version: 1.0
 */
@Api(tags = "工资API")
@RestController
@RequestMapping("/wage")
public class WageController {

    @Autowired
    private WageService wageService;

    @GetMapping("getAllWageOrder")
    @ApiOperation(value = "获取所有工资条信息")
    public List<WageOrderRes> getAllWageOrder(){
        return wageService.getAllWageOrder();
    }

    @PostMapping("addOrUpdateWageOrder")
    @ApiOperation(value = "添加或更新工资条信息")
    public BaseResponse addOrUpdateWageOrder(@RequestBody WageOrder wageOrder){
        return wageService.addOrUpdateWageOrder(wageOrder);
    }

    @GetMapping("deleteWageOrder/{id}")
    @ApiOperation(value = "删除工资条信息")
    public BaseResponse deleteWageOrder(@PathVariable int id){
        return wageService.deleteWageOrder(id);
    }
}
