package com.vilce.springboot_vue.config.filter;

import com.vilce.springboot_vue.service.AdminPermissionService;
import com.vilce.springboot_vue.utils.SpringContextUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
@Log4j2
public class URLPathMatchingFilter extends PathMatchingFilter {
    @Autowired
    private AdminPermissionService adminPermissionService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if (HttpMethod.OPTIONS.toString().equals((httpServletRequest).getMethod())) {
            httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }

        if (null == adminPermissionService) {
            adminPermissionService = SpringContextUtils.getContext().getBean(AdminPermissionService.class);
        }

        String requestAPI = getPathWithinApplication(request);

        Subject subject = SecurityUtils.getSubject();

        if (!subject.isAuthenticated()) {
            log.info("未登录用户尝试访问需要登录的接口");
            return false;
        }

        // 判断访问接口是否需要过滤（数据库中是否有对应信息）
        boolean needFilter = adminPermissionService.needFilter(requestAPI);
        if (!needFilter) {
            return true;
        } else {
            // 判断当前用户是否有相应权限
            boolean hasPermission = false;
            String username = subject.getPrincipal().toString();
            Set<String> permissionAPIs = adminPermissionService.listPermissionURLsByUser(username);
            for (String api : permissionAPIs) {
                // 匹配前缀
                if (requestAPI.startsWith(api)) {
                    hasPermission = true;
                    break;
                }
            }

            if (hasPermission) {
                log.trace("用户：" + username + "访问了：" + requestAPI + "接口");
                return true;
            } else {
                log.warn("用户：" + username + "访问了没有权限的接口：" + requestAPI);
                return false;
            }
        }
    }
}
