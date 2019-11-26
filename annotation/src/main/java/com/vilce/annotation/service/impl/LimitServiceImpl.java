package com.vilce.annotation.service.impl;

import com.vilce.annotation.config.rateLimite.RateLimit;
import com.vilce.annotation.service.LimitService;
import com.vilce.annotation.utils.IPUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.annotation.service.impl.LimitServiceImpl
 * @Author: 雷才哲
 * @Date: 2019/11/25 16:01
 * @Version: 1.0
 */
@Service
public class LimitServiceImpl implements LimitService {

    @Override
    @RateLimit(limitNum = 1.0)
    public String getIPAddr(HttpServletRequest request) {
        String ipAddr = IPUtil.getIpAddr(request);
        return ipAddr;
    }
}
