package com.vilce.actuator.security.config;

import com.vilce.common.utils.RequestUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @Description: 自定义权限登录拦截
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.actuator.security.config.DemoFilter
 * @Author: 雷才哲
 * @Date: 2020/6/23 11:03
 * @Version: 1.0
 */
public class DemoFilter extends GenericFilterBean {

    private Object principal = "anyUser";
    private List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ACTUATOR_USER");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //获取访问的资源
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        //获取ip
        String ip = servletRequest.getRemoteAddr();
        //判断是否内网
        boolean flag = RequestUtils.isInnerIp(ip);
        // 首先需要判断访问的是否是限制的资源
        if (uri.contains("actuator")) {
            //需要限制资源
            //判断是否内网
            if (flag) {
                //内网访问，不做限制
                if(SecurityContextHolder.getContext().getAuthentication() == null){
                    SecurityContextHolder.getContext().setAuthentication(
                            createAuthentication());
                }
            }else{
                //非内网访问，限制
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if(auth != null && auth instanceof UsernamePasswordAuthenticationToken){
                    if(principal.toString().equals(auth.getPrincipal().toString())){
                        SecurityContextHolder.getContext().setAuthentication(null);
                    }
                }
            }
        }else {
            //不需要限制资源
            if(SecurityContextHolder.getContext().getAuthentication() == null){
                SecurityContextHolder.getContext().setAuthentication(
                        createAuthentication());
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    protected Authentication createAuthentication() {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                principal, "N/A", authorities);
        return auth;
    }
}
