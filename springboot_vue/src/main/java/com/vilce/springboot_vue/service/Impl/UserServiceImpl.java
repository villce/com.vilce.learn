package com.vilce.springboot_vue.service.Impl;

import com.vilce.springboot_vue.mapper.UserMapper;
import com.vilce.springboot_vue.model.po.AdminRole;
import com.vilce.springboot_vue.model.po.User;
import com.vilce.springboot_vue.service.AdminRoleService;
import com.vilce.springboot_vue.service.AdminUserRoleService;
import com.vilce.springboot_vue.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

/**
 * @Description: 用户相关服务实现
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.Impl.UserServiceImpl
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
    public List<User> listAllUsers() {
        // 获取所有用户信息
        List<User> userList = userMapper.findAll();
        // 获取用户对应的角色信息
        userList.forEach(u -> {
            List<AdminRole> roles = adminRoleService.getRolesByUserId(u.getId());
            u.setRoles(roles);
        });
        return userList;
    }

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    @Override
    public User getUserByUsername(String username) {
        // todo 与isExist重复，是否合并
        return userMapper.getUserByUsername(username);
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Override
    public boolean addUser(User user) {
        String username = user.getUsername();
        String name = user.getName();
        String phone = user.getPhone();
        String email = user.getEmail();
        String password = user.getPassword();

        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        phone = HtmlUtils.htmlEscape(phone);
        user.setPhone(phone);
        email = HtmlUtils.htmlEscape(email);
        user.setEmail(email);
        user.setEnabled(true);
        if (username.equals("") || password.equals("")) {
            // todo 返回message "用户名和密码不能为空"
            return false;
        }
        User userFind = userMapper.getUserByUsername(username);
        if (ObjectUtils.isNotEmpty(userFind)) {
            // todo 返回message "用户已存在"
            return false;
        }
        // 默认生成 16 位盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();

        user.setSalt(salt);
        user.setPassword(encodedPassword);

        boolean result = userMapper.addUser(user);
        if (result) {
            // todo 返回message "注册成功"
            return true;
        } else {
            // todo 返回message "注册失败，未知错误"
            return false;
        }
    }

    /**
     * 更新用户状态信息
     *
     * @param user
     */
    @Override
    public boolean updateUserStatus(User user) {
        if (userMapper.updateUserStatus(user)) {
            // todo 返回message "更新状态成功"
            return true;
        } else {
            // todo 返回message "更新状态失败"
            return false;
        }
    }

    /**
     * 重置密码
     *
     * @param requestUser
     * @return
     */
    @Override
    public boolean updatePassword(User requestUser) {
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        requestUser.setSalt(salt);
        String encodedPassword = new SimpleHash("md5", "123", salt, times).toString();
        requestUser.setPassword(encodedPassword);
        if (userMapper.updatePassword(requestUser)) {
            // todo 返回message "密码更新成功"
            return true;
        } else {
            // todo 返回massge "密码更新失败"
            return false;
        }
    }

    /**
     * 更新用户基础信息
     *
     * @param requestUser
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean editUser(User requestUser) {
        if (userMapper.updateUserInfo(requestUser)) {
            if (adminUserRoleService.updateRoleChanges(requestUser.getId(), requestUser.getRoles())) {
                // todo 返回message "用户基础信息更新成功"
                return true;
            } else {
                // todo 返回message "用户角色信息更新失败"
                return false;
            }
        } else {
            // todo 返回message "用户基础信息更新失败"
            return false;
        }
    }
}
