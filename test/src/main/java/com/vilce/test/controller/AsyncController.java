package com.vilce.test.controller;

import com.vilce.test.model.po.User;
import com.vilce.test.model.vo.UserRes;
import com.vilce.test.service.AsyncService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.test.controller.AsyncController
 * @Author: 雷才哲
 * @Date: 2019/12/6 16:23
 * @Version: 1.0
 */
@Api(tags = "异步接口")
@RestController
@RequestMapping("/async")
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @PostMapping("async_test")
    @ApiOperation(value = "异步接口测试")
    public List<UserRes> asyncTest(@RequestBody @Valid User user){
        return asyncService.asyncTest(user);
    }

    @PostMapping("async_test1")
    @ApiOperation(value = "异步接口测试")
    public String send(String name){
        return asyncService.send(name);
    }
}
