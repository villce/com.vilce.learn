package com.vilce.actuator.security.config;

import com.vilce.common.utils.RequestUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.actuator.security.config.CustomIpAuthenticationProvider
 * @Author: 雷才哲
 * @Date: 2020/6/22 13:51
 * @Version: 1.0
 */
//@Component
public class CustomIpAuthenticationProvider implements AuthenticationProvider {
    Set<String> whitelist = new HashSet<String>();

    public CustomIpAuthenticationProvider() {
        super();
        whitelist.add("0:0:0:0:0:0:0:1");
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userIp = RequestUtils.getClientIp(request);
        //限制只有白名单或内网ip能访问
        if (whitelist.contains(userIp) || RequestUtils.isInnerIp(userIp)) {
            String name = auth.getName();
            String password = auth.getCredentials().toString();
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new UsernamePasswordAuthenticationToken(name, password, authorities);
        } else {
            throw new BadCredentialsException("该IP无权访问");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
