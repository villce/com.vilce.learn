package com.vilce.rabbitmq.controller;

import com.google.common.collect.Lists;
import com.vilce.common.model.log.utils.LoggerUtils;
import com.vilce.rabbitmq.service.KafkaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: description
 * @ProjectName: learn
 * @Package: com.vilce.test.controller
 * @Author: 雷才哲
 * @Date: 2021/6/22 上午9:49
 * @Version: 1.0
 */
@RestController
@Api(tags = "kafka控制器")
@RequestMapping(value = "kafka")
public class KafkaController {

    @Autowired
    private KafkaService kafkaUtils;

    @ApiOperation(value = "新增topic")
    @PostMapping("add")
    public String add(String topic) {
        NewTopic newTopic = new NewTopic(topic, 3, (short) 1);
        kafkaUtils.createTopic(Lists.newArrayList(newTopic));
        return "添加消息成功！";
    }

    @ApiOperation(value = "查询topic信息")
    @GetMapping("getTopicInfo/{topic}")
    public String getTopicInfo(@PathVariable String topic) {
        return kafkaUtils.getTopicInfo(Lists.newArrayList(topic));
    }

    @ApiOperation("删除topic")
    @DeleteMapping("delete/{topic}")
    public String delete(@PathVariable String topic) {
        kafkaUtils.deleteTopic(Lists.newArrayList(topic));
        return "消息删除成功！";
    }

    @ApiOperation(value = "查询所有topic")
    @GetMapping("getAllTopic")
    public List<String> getAllTopic() {
        return kafkaUtils.getAllTopic();
    }

    @ApiOperation(value = "往topic发送消息")
    @PostMapping("sendMessage")
    public String sendMessage(String topic, String message) {
        kafkaUtils.sendMessage(topic, message);
        return "发送消息成功！";
    }

    @KafkaListener(topics = {"topic1", "topic2", "topic3"})
    public void consume(String message) {
        LoggerUtils.info(this.getClass(), "receive msg: " + message);
    }
}
