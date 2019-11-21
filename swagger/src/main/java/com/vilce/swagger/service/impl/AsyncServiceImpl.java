package com.vilce.swagger.service.impl;

import com.vilce.swagger.po.Shop;
import com.vilce.swagger.po.Task;
import com.vilce.swagger.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.AsyncServiceImpl
 * @Author: 雷才哲
 * @Date: 2019/11/11 10:53
 * @Version: 1.0
 */
@Service
public class AsyncServiceImpl implements AsyncService {

    @Override
    @Async
    public Future<Task> task1() throws InterruptedException {
        Task task = new Task();
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(10);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task1任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        task.setResult(true);
        return new AsyncResult<>(task);
    }

    @Override
    @Async
    public Future<Task> task2() throws InterruptedException {
        Task task = new Task();
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(10);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task2任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        task.setResult(true);
        return new AsyncResult<>(task);
    }

    @Override
    @Async
    public Future<Task> task3() throws InterruptedException {
        Task task = new Task();
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(10);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task3任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        task.setResult(true);
        return new AsyncResult<>(task);
    }

    @Override
    public String task4() {
        long currentTimes = System.currentTimeMillis();
        CompletableFuture<Task> future1 = CompletableFuture.supplyAsync(() -> {
            Task task = new Task();
            try {
                long time1 = System.currentTimeMillis();
                Thread.sleep(10);
                long time1s = System.currentTimeMillis();
                System.out.println("task1耗时：" + (time1s - time1) + "ms");
                task.setResult(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return task;
        });
        CompletableFuture<Task> future2 = CompletableFuture.supplyAsync(() -> {
            Task task = new Task();
            try {
                long time1 = System.currentTimeMillis();
                Thread.sleep(20);
                long time1s = System.currentTimeMillis();
                System.out.println("task2耗时：" + (time1s - time1) + "ms");
                task.setResult(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return task;
        });
        CompletableFuture<Task> future3 = CompletableFuture.supplyAsync(() -> {
            Task task = new Task();
            try {
                long time1 = System.currentTimeMillis();
                Thread.sleep(30);
                long time1s = System.currentTimeMillis();
                System.out.println("task3耗时：" + (time1s - time1) + "ms");
                task.setResult(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return task;
        });
        try {
            if (future1.get().isResult() && future2.get().isResult() && future3.get().isResult()) {
                long currentTimel = System.currentTimeMillis();
                return "task耗时：" + (currentTimel - currentTimes) + "ms";
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "异步失败";
    }

    @Override
    public String task5() {
        Shop shop = new Shop("bestShop");
        long start = System.currentTimeMillis();
        CompletableFuture<Double> futurePrice = CompletableFuture.supplyAsync(()->{
            return shop.getPrice("some product");
        });
        System.out.println("调用返回，耗时"+(System.currentTimeMillis() - start));
        try {
            double price = futurePrice.get();
            System.out.println("价格："+price);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return ("价格返回，耗时"+(System.currentTimeMillis() - start));
    }

    @Override
    public String task6() {
        Executor executor = Executors.newFixedThreadPool( 12);
        Shop shop = new Shop("bestShop");
        long start = System.currentTimeMillis();
        CompletableFuture<Double> futurePrice = CompletableFuture.supplyAsync(()->{
            return shop.getPrice("some product");
        },executor);
        System.out.println("调用返回，耗时"+(System.currentTimeMillis() - start));
        try {
            double price = futurePrice.get();
            System.out.println("价格："+price);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return ("价格返回，耗时"+(System.currentTimeMillis() - start));
    }
}
