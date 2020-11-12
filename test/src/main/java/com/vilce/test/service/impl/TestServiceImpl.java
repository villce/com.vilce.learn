package com.vilce.test.service.impl;

import com.vilce.test.service.TestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.test.service.impl.TestServiceImpl
 * @Author: 雷才哲
 * @Date: 2020/11/12 14:53
 * @Version: 1.0
 */
@Service
public class TestServiceImpl implements TestService {

    @Value("${text}")
    private String text;
    
    @Override
    public String text() {
        return text;
    }
}
