package com.vilce.springboot_vue.config.shiro;


import com.vilce.springboot_vue.model.po.User;
import com.vilce.springboot_vue.service.AdminPermissionService;
import com.vilce.springboot_vue.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.Set;

/**
 * @Description: WJRealm配置
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.config.shiro.WJRealm
 * @Author: 雷才哲
 * @Date: 2020/8/26 16:02
 * @Version: 1.0
 */
public class WJRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private AdminPermissionService adminPermissionService;

    /**
     * 重写获取授权信息方法
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取当前用户的所有权限
        String username = principalCollection.getPrimaryPrincipal().toString();
        Set<String> permissions = adminPermissionService.listPermissionURLsByUser(username);

        // 将权限放入授权信息中
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
        s.setStringPermissions(permissions);
        return s;
    }

    /**
     * 获取认证信息，即根据 token 中的用户名从数据库中获取密码、盐等并返回
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = token.getPrincipal().toString();
        User user = userService.getUserByUsername(userName);
        if (ObjectUtils.isEmpty(user)) {
            throw new UnknownAccountException();
        }
        String passwordInDB = user.getPassword();
        String salt = user.getSalt();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, passwordInDB, ByteSource.Util.bytes(salt), getName());
        return authenticationInfo;
    }
}
