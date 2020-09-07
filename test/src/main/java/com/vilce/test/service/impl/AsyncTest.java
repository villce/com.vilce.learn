package com.vilce.test.service.impl;

import com.vilce.common.utils.JSONUtils;
import com.vilce.test.model.po.User;
import com.vilce.test.model.vo.UserRes;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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

    public static void main(String[] args) throws ParseException {
        String a = "测试1";
        Calendar b = Calendar.getInstance();
        int c = 0;
        test1(a, b, c);
        test2(a, b, c);
        List<String> a1 = new ArrayList<>();
        a1.add("1");
        a1.add("2");
        List<String> a2 = a1;
        a2.add("3");
        System.out.println(JSONUtils.toJsonPretty(a1));
        System.out.println(JSONUtils.toJsonPretty(a2));
    }

    private static void test2(String str, Calendar cal, int count) {
        System.out.println(str);
        System.out.println(cal.getTime());
        System.out.println(count);
    }

    public static void test1(String str, Calendar cal, int count) {
        str = StringUtils.join(str, "改变");
        System.out.println(str);
        cal.add(Calendar.DATE, 1);
        System.out.println(cal.getTime());
        count = count + 1;
        System.out.println(count);
    }
}
