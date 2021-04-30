package com.vilce.springboot_vue.module.user.service.impl;

import com.vilce.common.model.enums.ResultStatus;
import com.vilce.common.model.exception.BasicException;
import com.vilce.common.model.po.BaseResponse;
import com.vilce.springboot_vue.module.user.mapper.UserMapper;
import com.vilce.springboot_vue.module.user.model.po.AdminRole;
import com.vilce.springboot_vue.module.user.model.po.AdminUser;
import com.vilce.springboot_vue.module.user.model.vo.UserReq;
import com.vilce.springboot_vue.module.user.service.AdminRoleService;
import com.vilce.springboot_vue.module.user.service.AdminUserRoleService;
import com.vilce.springboot_vue.module.user.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description: 用户相关服务实现
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.user.service.impl.UserServiceImpl
 * @Author: 雷才哲
 * @Date: 2019/12/20 16:39
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private AdminUserRoleService adminUserRoleService;

    /**
     * 列出所有用户信息
     *
     * @return
     */
    @Override
    public List<AdminUser> listAllUsers() {
        // 获取所有用户信息
        List<AdminUser> userList = userMapper.findAll();
        // 获取用户对应的角色信息
        userList.forEach(u -> {
            List<AdminRole> roles = adminRoleService.getRolesByUserId(u.getId());
            u.setRoles(roles);
        });
        return userList;
    }

    /**
     * 获取指定用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public AdminUser findUser(int userId) {
        AdminUser user = userMapper.getUserById(userId);
        List<AdminRole> roles = adminRoleService.getRolesByUserId(user.getId());
        user.setRoles(roles);
        return user;
    }

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    @Override
    public AdminUser getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    /**
     * 添加用户
     *
     * @param userReq
     * @return
     */
    @Override
    public BaseResponse addUser(UserReq userReq) {
        String username = userReq.getUsername();
        String password = userReq.getPassword();

        username = HtmlUtils.htmlEscape(username);
        userReq.setUsername(username);
        userReq.setEnabled(true);
        if (username.equals("") || password.equals("")) {
            throw new BasicException(ResultStatus.ERROR.getStatus(), "用户名和密码不能为空!");
        }
        AdminUser userFind = userMapper.getUserByUsername(username);
        if (ObjectUtils.isNotEmpty(userFind)) {
            throw new BasicException(ResultStatus.ERROR.getStatus(), "用户已存在!");
        }
        // 默认生成 16 位盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();

        userReq.setSalt(salt);
        userReq.setPassword(encodedPassword);
        // 注册用户，同步添加游客权限
        if (userMapper.addUser(userReq) && adminUserRoleService.addUserRole(userReq.getId(), 3)) {
            return BaseResponse.buildResponse(0, "注册成功！");
        } else {
            throw new BasicException(ResultStatus.ERROR.getStatus(), "注册失败，未知错误！");
        }
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    @Override
    public AdminUser currentUser() {
        String username = getCookie("vilce_token");
        if (StringUtils.isNotBlank(username)) {
            return getUserByUsername(username);
        }
        return null;
    }

    /**
     * 更新用户状态信息
     *
     * @param user
     */
    @Override
    public BaseResponse updateUserStatus(AdminUser user) {
        if (userMapper.updateUserStatus(user)) {
            return BaseResponse.buildResponse(0, "更新用户状态成功！");
        } else {
            throw new BasicException(ResultStatus.ERROR.getStatus(), "更新用户状态失败!");
        }
    }

    /**
     * 重置密码
     *
     * @param requestUser
     * @return
     */
    @Override
    public BaseResponse updatePassword(AdminUser requestUser) {
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        requestUser.setSalt(salt);
        String encodedPassword = new SimpleHash("md5", "123", salt, times).toString();
        requestUser.setPassword(encodedPassword);
        if (userMapper.updatePassword(requestUser)) {
            return BaseResponse.buildResponse(0, "更新用户密码成功！");
        } else {
            throw new BasicException(ResultStatus.ERROR.getStatus(), "更新用户密码失败!");
        }
    }

    /**
     * 更新用户基础信息
     *
     * @param requestUser
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse editUser(AdminUser requestUser) {
        // 更新用户名或头像
        userMapper.updateUserInfo(requestUser);
        BaseResponse baseResponse = adminUserRoleService.updateRoleChanges(requestUser.getId(), requestUser.getRoles());
        if (baseResponse.getStatus() == 0) {
            return BaseResponse.buildResponse(0, "更新用户信息成功！");
        } else {
            throw new BasicException(ResultStatus.ERROR.getStatus(), "更新用户信息失败！");
        }
    }

    /**
     * 获取cookie的值
     * @param name
     * @return
     */
    public String getCookie(String name) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Cookie[] cookies = request.getCookies();
        if(null == cookies || cookies.length == 0) {
            return null;
        }
        for(Cookie c : cookies) {
            if(c.getName().equals(name)) {
                return c.getValue();
            }
        }
        return null;
    }
}
