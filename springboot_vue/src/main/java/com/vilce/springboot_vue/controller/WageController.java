package com.vilce.springboot_vue.controller;

import com.vilce.springboot_vue.model.po.WageOrder;
import com.vilce.springboot_vue.model.vo.respones.WageOrderRes;
import com.vilce.springboot_vue.service.WageService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.controller.WageController
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

    @CrossOrigin
    @GetMapping("getAllWageOrder")
    public List<WageOrderRes> getAllWageOrder(){
        return wageService.getAllWageOrder();
    }

    @CrossOrigin
    @PostMapping("addWageOrder")
    public boolean addWageOrder(WageOrder wageOrder){
        return wageService.addWageOrder(wageOrder);
    }

    @CrossOrigin
    @PostMapping("updateWageOrder")
    public boolean updateWageOrder(WageOrder wageOrder){
        return wageService.updateWageOrder(wageOrder);
    }

    @CrossOrigin
    @PostMapping("deleteWageOrder")
    public boolean deleteWageOrder(Long eid){
        return wageService.deleteWageOrder(eid);
    }
}
