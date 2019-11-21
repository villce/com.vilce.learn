package com.vilce.swagger.po;

import lombok.Data;

import java.util.Random;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.Shop
 * @Author: 雷才哲
 * @Date: 2019/11/11 14:14
 * @Version: 1.0
 */
@Data
public class Shop {
    private String name;

    public Shop(String name) {
        super();
        this.name = name;
    }

    Random random = new Random();

    public void delay() {
        try {
            Thread.sleep(10L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public double getPrice(String product) {
        delay();
        return random.nextDouble() * 100;
    }
}
