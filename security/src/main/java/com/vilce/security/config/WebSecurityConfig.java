package com.vilce.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description: Security配置
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.security.config.WebSecurityConfig
 * @Author: 雷才哲
 * @Date: 2019/12/10 19:48
 * @Version: 1.0
 */
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 匹配 "/" 路径，不需要权限即可访问
     * 匹配 "/user" 及其以下所有路径，都需要 "USER" 权限
     * 登录地址为 "/login"，登录成功默认跳转到页面 "/user"
     * 退出登录的地址为 "/logout"，退出成功后跳转到页面 "/login"
     * 默认启用 CSRF
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/user")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login");
    }

    /**
     * 在内存中创建一个名为 "user" 的用户，密码为 "pwd"，拥有 "USER" 权限
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("anoyi")
                .password(encoder.encode("pwd")).roles("USER");
    }

}
