package com.vilce.springboot_vue.controller;

import com.vilce.springboot_vue.model.po.User;
import com.vilce.springboot_vue.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

/**
 * @Description: 登录相关API
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.controller.LoginController
 * @Author: 雷才哲
 * @Date: 2020/8/26 16:36
 * @Version: 1.0
 */
@RestController
@RequestMapping("/login")
@Api(tags = "登录相关API")
public class LoginController {

    @Autowired
    private UserService userService;

//    @PostMapping("/api/login")
    @PostMapping("in")
    @ApiOperation(value = "用户登录")
    public Boolean login(@RequestBody User requestUser) {
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, requestUser.getPassword());
        usernamePasswordToken.setRememberMe(true);
        try {
            subject.login(usernamePasswordToken);
            User user = userService.getUserByUsername(username);
            if (!user.isEnabled()) {
                // todo 设置返回message "该用户已被禁用"
                return false;
            }
            // todo 设置返回message username
            return true;
        } catch (IncorrectCredentialsException e) {
            // todo 设置返回message "密码错误"
            return false;
        } catch (UnknownAccountException e) {
            // todo 设置返回message "账户不存在"
            return false;
        }
    }

//    @PostMapping("/api/register")
    @PostMapping("register")
    @ApiOperation(value = "用户注册")
    public boolean register(@RequestBody User user) {
        return userService.addUser(user);
    }

//    @GetMapping("/api/logout")
    @GetMapping("out")
    @ApiOperation(value = "退出登录")
    public boolean logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        // todo 返回message "成功登出"
        return true;
    }

//    @GetMapping("/api/authentication")
    @GetMapping("authentication")
    @ApiOperation("身份验证")
    public String authentication() {
        return "身份认证成功";
    }
}
