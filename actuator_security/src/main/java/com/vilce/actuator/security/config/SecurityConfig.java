package com.vilce.actuator.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.actuator.security.config.SecurityConfig
 * @Author: 雷才哲
 * @Date: 2020/6/22 13:49
 * @Version: 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private CustomIpAuthenticationProvider authenticationProvider;
//
//    /**
//     * 此处重写的方法适合ip数比较多的情况
//     */
//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        //简化授权配置
//        //auth.inMemoryAuthentication().withUser("vilce").password("123").authorities("ROLE_USER");
//
//        //与上面注释掉的功能一样,只是这里需要下面的authenticationProvider 类而上面的不需要
//        auth.authenticationProvider(authenticationProvider);
//    }

    /**
     * 此处重写方法可以单独使用，适合IP个数比较少的情况
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new DemoFilter(), AnonymousAuthenticationFilter.class)
                .authorizeRequests()
                //对于所有的ip，即使不在白名单中也能访问此处资源
                //.antMatchers("/test/**").permitAll()
                //特定ip可以不登录获取资源
                .antMatchers("/actuator/**").hasAuthority("ACTUATOR_USER")
                //特定ip必须登录才能获取
                //.antMatchers("/test/**").access("isAuthenticated() and hasIpAddress('0:0:0:0:0:0:0:1')")
                .anyRequest().permitAll()
                .and().csrf().disable();
    }
}
