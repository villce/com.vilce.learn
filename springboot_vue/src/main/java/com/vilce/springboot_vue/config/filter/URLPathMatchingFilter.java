package com.vilce.springboot_vue.config.filter;

import com.vilce.common.model.log.utils.LoggerUtils;
import com.vilce.common.utils.RequestUtils;
import com.vilce.springboot_vue.module.user.service.AdminPermissionService;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @Description: URL路径拦截
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.config.filter.URLPathMatchingFilter
 * @Author: 雷才哲
 * @Date: 2020/8/26 14:45
 * @Version: 1.0
 */
public class URLPathMatchingFilter extends PathMatchingFilter {
    @Autowired
    private AdminPermissionService adminPermissionService;
    @Autowired
    private RedisTemplate redisTemplate;

    private static final String SHIRO_SESSION = "com:vilce:shiro:";

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if (HttpMethod.OPTIONS.toString().equals((httpServletRequest).getMethod())) {
            httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }
        // 获取接口地址
        String requestAPI = getPathWithinApplication(request);
        // 获取sessionID
        String sessionId = getCookie("SHRIOSESSIONID");
        if (!redisTemplate.hasKey(SHIRO_SESSION + sessionId)) {
            LoggerUtils.info(URLPathMatchingFilter.class, "未登录用户尝试访问需要登录的接口");
            return false;
        }
        // 判断访问接口是否需要过滤
        boolean needFilter = adminPermissionService.needFilter(requestAPI);
        if (!needFilter) {
            return true;
        } else {
            // 判断当前用户是否有相应权限
            boolean hasPermission = false;
            String username = getCookie("vilce_token");
            Set<String> permissionAPIs = adminPermissionService.listPermissionURLsByUser(username);
            for (String api : permissionAPIs) {
                // 匹配前缀
                if (requestAPI.startsWith(api)) {
                    hasPermission = true;
                    break;
                }
            }
            if (hasPermission) {
                LoggerUtils.info(URLPathMatchingFilter.class, "用户：" + username + "访问了：" + requestAPI + "接口");
                return true;
            } else {
                LoggerUtils.warn(URLPathMatchingFilter.class, "用户：" + username + "访问了没有权限的接口：" + requestAPI);
                return false;
            }
        }
    }

    /**
     * 获取cookie的值
     * @param name
     * @return
     */
    public String getCookie(String name) {
        HttpServletRequest request = RequestUtils.getRequest();
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
