package com.vilce.annotation.api;

import com.vilce.annotation.model.BaseResponse;
import com.vilce.annotation.model.po.User;
import com.vilce.annotation.model.vo.UserRes;
import com.vilce.annotation.utils.JSONUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.annotation.api.TestController
 * @Author: 雷才哲
 * @Date: 2019/11/13 15:08
 * @Version: 1.0
 */
@RestController
@RequestMapping("/test")
@Api(tags = "注解测试")
public class TestController {

    @ApiOperation(value = "异常拦截")
    @GetMapping("exception")
    public UserRes Exception(){
        UserRes userRes = new UserRes();
        List<String> list = new ArrayList();
        userRes.setName(list.get(0));
        return userRes;
    }

    @ApiOperation(value = "JSON测试")
    @GetMapping("json")
    public UserRes JsonTest(){
        User user = new User();
        user.setName("vilce");
        user.setAge(1);
        String userString = JSONUtils.toJSONString(user);
        System.out.println(userString);
        return JSONUtils.toJavaBean(userString,UserRes.class);
    }
}
