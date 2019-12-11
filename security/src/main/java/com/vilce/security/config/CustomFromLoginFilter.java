package com.vilce.security.config;

import io.swagger.models.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 自定义filter
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.security.config.CustomFromLoginFilter
 * @Author: 雷才哲
 * @Date: 2019/12/11 17:04
 * @Version: 1.0
 */
public class CustomFromLoginFilter extends AbstractAuthenticationProcessingFilter {
    CustomFromLoginFilter(String defaultFilterProcessesUrl) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl, HttpMethod.POST.name()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException {
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        customCheck(username, password);
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        return new UsernamePasswordAuthenticationToken(username, password, simpleGrantedAuthorities);
    }

    /**
     * 只允许用户'vilce'登录
     * @param username
     * @param password
     */
    private void customCheck(String username, String password){
        if (!("vilce".equals(username) && "vilce".equals(password))){
            throw new RuntimeException("用户名或密码错误！");
        }

    }
}
