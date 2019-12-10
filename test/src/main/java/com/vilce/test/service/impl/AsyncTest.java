package com.vilce.test.service.impl;

import com.vilce.common.utils.JSONUtils;
import com.vilce.test.model.po.User;
import com.vilce.test.model.vo.UserRes;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.test.service.impl.AsyncTest
 * @Author: 雷才哲
 * @Date: 2019/12/6 17:46
 * @Version: 1.0
 */
@Component
public class AsyncTest {
    @Async("asyncExecutor")
    public Future<UserRes> userUp(User user) throws InterruptedException {
        System.out.println("user：" + JSONUtils.toJson(user));
        UserRes userRes = new UserRes();
        userRes.setName(user.getName());
        userRes.setAge(user.getAge() + 1);
        Thread.sleep(1000L);
        System.out.println("userRes：" + JSONUtils.toJson(userRes));
        return new AsyncResult<>(userRes);
    }

}
