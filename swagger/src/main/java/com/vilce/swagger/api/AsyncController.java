package com.vilce.swagger.api;

import com.vilce.swagger.po.Task;
import com.vilce.swagger.service.AsyncService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.AsyncController
 * @Author: 雷才哲
 * @Date: 2019/11/11 10:20
 * @Version: 1.0
 */
@RestController
@Api(tags = "异步测试")
@RequestMapping("/async")
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("test1")
    @ApiOperation(value = "注解异步")
    public String doTask1() throws InterruptedException{
        long currentTimeMillis = System.currentTimeMillis();
        Future<Task> task1 = asyncService.task1();
        Future<Task> task2 = asyncService.task2();
        Future<Task> task3 = asyncService.task3();
        String result = null;
        try {
            if(task1.get().isResult() && task2.get().isResult() && task3.get().isResult()) {
                // 三个任务都调用完成，退出循环等待
                long currentTimeMillis1 = System.currentTimeMillis();
                result = "task任务总耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms";
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("test2")
    @ApiOperation(value = "异步")
    public String doTask2() {
        return asyncService.task5();
    }

    @GetMapping("test3")
    @ApiOperation(value = "异步")
    public String doTask3() {
        return asyncService.task6();
    }
}
