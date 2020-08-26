package com.vilce.springboot_vue.controller;

import com.vilce.springboot_vue.model.po.User;
import com.vilce.springboot_vue.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description: 用户相关API
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.controller.UserController
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

//    @GetMapping("/api/admin/user")
    @GetMapping("getAllUsers")
    @ApiOperation(value = "列出所有用户信息")
    public List<User> listAllUsers() {
        return userService.listAllUsers();
    }

//    @PutMapping("/api/admin/user/status")
    @PutMapping("updateUserStatus")
    @ApiOperation(value = "更新用户状态信息")
    public boolean updateUserStatus(@RequestBody @Valid User requestUser) {
        return  userService.updateUserStatus(requestUser);
    }

//    @PutMapping("/api/admin/user/password")
    @PutMapping("resetPassword")
    @ApiOperation(value = "重置密码")
    public boolean resetPassword(@RequestBody @Valid User requestUser) {
        return userService.updatePassword(requestUser);
    }

//    @PutMapping("/api/admin/user")
    @PutMapping("editUser")
    @ApiOperation(value = "更新用户基础信息")
    public boolean editUser(@RequestBody @Valid User requestUser) {
        return userService.editUser(requestUser);
    }
}
