package com.vilce.test.service.impl;

import com.vilce.common.model.annotation.RateLimit;
import com.vilce.common.model.enums.TimeEnum;
import com.vilce.common.utils.RequestUtils;
import com.vilce.test.model.po.User;
import com.vilce.test.service.LimitService;
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
    @RateLimit(limitNum = 1.0, limitName = "", timeUnit = TimeEnum.SECOND)
    public String getIPAddr(HttpServletRequest request) {
        String ipAddr = RequestUtils.getClientIp(request);
        return ipAddr;
    }

    @Override
    @RateLimit(limitNum = 1.0, limitName = "name", timeUnit = TimeEnum.SECOND)
    public String nameLimiter(User user) {
        return user.getName();
    }
}
