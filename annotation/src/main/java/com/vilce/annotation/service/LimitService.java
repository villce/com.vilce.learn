package com.vilce.annotation.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.annotation.service.LimitService
 * @Author: 雷才哲
 * @Date: 2019/11/25 16:00
 * @Version: 1.0
 */
public interface LimitService {

    String getIPAddr(HttpServletRequest request);
}
