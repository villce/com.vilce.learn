package com.vilce.test.service.impl;

import com.vilce.test.service.TestService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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

    @Value("${spring.vilce.springbootvue}")
    private String springbootVue;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String text() {
        String url = StringUtils.join(springbootVue, "//article/listArticles?page=1&size=1");
        String responseStr = restTemplate.getForObject(url, String.class);
        return responseStr;
    }

    @Override
    public String log(String name) {
        return name;
    }

    public static void main(String[] args) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            String a = "test1";
            System.out.println(a);
            return a;
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(()->{
            String b = "test2";
            System.out.println(b);
            return b;
        });
        try {
            String c = StringUtils.join(future1.get(), future2.get());
            System.out.println(c);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
