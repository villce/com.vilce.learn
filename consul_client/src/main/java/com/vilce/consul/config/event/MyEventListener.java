package com.vilce.consul.config.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.consul.config.event.MyEventListener
 * @Author: 雷才哲
 * @Date: 2020/12/24 15:32
 * @Version: 1.0
 */
@Component
public class MyEventListener implements ApplicationListener<MyEvent> {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void onApplicationEvent(MyEvent event) {
        if (event.isResult()) {
            String response = restTemplate.getForObject("http://localhost:8003/test/hello2", String.class);
            System.out.println(response);
        }
    }
}
