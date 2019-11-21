package com.vilce.swagger.service;

import com.vilce.swagger.po.Task;

import java.util.concurrent.Future;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.AsyncService
 * @Author: 雷才哲
 * @Date: 2019/11/11 10:53
 * @Version: 1.0
 */
public interface AsyncService {
    public Future<Task> task1() throws InterruptedException;

    public Future<Task> task2() throws InterruptedException;

    public Future<Task> task3() throws InterruptedException;

    String task4() throws InterruptedException;

    String task5();

    String task6();
}
