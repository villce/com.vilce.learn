package com.vilce.springboot_vue.config.shiro;

import com.vilce.springboot_vue.module.user.model.po.AdminUser;
import com.vilce.springboot_vue.module.user.service.AdminPermissionService;
import com.vilce.springboot_vue.module.user.service.UserService;
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
 * @ProjectName: operate
 * @Package: com.eastmoney.emis.operate.config.shiro
 * @Author: 雷才哲
 * @Date: 2021/3/22 下午1:36
 * @Version: 1.0
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private AdminPermissionService adminPermissionService;

    /**
     * 重写角色权限配置
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
     * 重写身份验证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = authenticationToken.getPrincipal().toString();
        AdminUser user = userService.getUserByUsername(userName);
        if (ObjectUtils.isEmpty(user)) {
            throw new UnknownAccountException();
        }
        String passwordInDB = user.getPassword();
        String salt = user.getSalt();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, passwordInDB, ByteSource.Util.bytes(salt), getName());
        return authenticationInfo;
    }
}
