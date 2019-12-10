package com.vilce.test.service.impl;

import com.vilce.test.model.po.User;
import com.vilce.test.model.vo.UserRes;
import com.vilce.test.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.test.service.impl.AsyncServiceImpl
 * @Author: 雷才哲
 * @Date: 2019/12/6 16:24
 * @Version: 1.0
 */
@Service
public class AsyncServiceImpl implements AsyncService {

    @Autowired
    private AsyncTest asyncTest;

    @Override
    public List<UserRes> asyncTest(User user) {
        List<UserRes> userResList = new ArrayList<>();
        try {
            Future<UserRes> future1 = asyncTest.userUp(user);
            Future<UserRes> future2 = asyncTest.userUp(user);
            Future<UserRes> future3 = asyncTest.userUp(user);
            for (; ; ) {
                if (future1.isDone() && future2.isDone() && future3.isDone()) {
                    break;
                }
            }
            userResList.add(future1.get());
            userResList.add(future2.get());
            userResList.add(future3.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return userResList;
    }

    @Async
    @Override
    public String send(String name) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return name;
    }
}
