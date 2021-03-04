package com.vilce.springboot_vue.module.user.controller;

import com.vilce.common.model.po.BaseResponse;
import com.vilce.springboot_vue.module.user.model.po.AdminUser;
import com.vilce.springboot_vue.module.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description: 用户相关API
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.user.controller.UserController
 * @Author: 雷才哲
 * @Date: 2020/8/26 16:36
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关API")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("getAllUsers")
    @ApiOperation(value = "列出所有用户信息")
    public List<AdminUser> listAllUsers() {
        return userService.listAllUsers();
    }

    @GetMapping("findUser/{userId}")
    @ApiOperation(value = "获取指定用户信息")
    public AdminUser findUser(@PathVariable int userId) {
        return userService.findUser(userId);
    }

    @PutMapping("updateUserStatus")
    @ApiOperation(value = "更新用户状态信息")
    public BaseResponse updateUserStatus(@RequestBody @Valid AdminUser requestUser) {
        return  userService.updateUserStatus(requestUser);
    }

    @PutMapping("resetPassword")
    @ApiOperation(value = "重置密码")
    public BaseResponse resetPassword(@RequestBody @Valid AdminUser requestUser) {
        return userService.updatePassword(requestUser);
    }

    @PutMapping("editUser")
    @ApiOperation(value = "更新用户基础信息")
    public BaseResponse editUser(@RequestBody @Valid AdminUser requestUser) {
        return userService.editUser(requestUser);
    }
}
