package com.vilce.rabbitmq.service;

import org.apache.kafka.clients.admin.NewTopic;

import java.util.Collection;
import java.util.List;

/**
 * @Description: description
 * @ProjectName: learn
 * @Package: com.vilce.springboot_vue.module.tool.service
 * @Author: 雷才哲
 * @Date: 2021/6/22 上午10:31
 * @Version: 1.0
 */
public interface KafkaService {

    /**
     * 新增topic，支持批量
     *
     * @param newTopics
     */
    void createTopic(Collection<NewTopic> newTopics);

    /**
     * 删除topic，支持批量
     *
     * @param newTopics
     */
    void deleteTopic(Collection<String> newTopics);

    /**
     * 获取指定topic的信息
     *
     * @param topics
     * @return
     */
    String getTopicInfo(Collection<String> topics);

    /**
     * 获取全部topic
     *
     * @return
     */
    List<String> getAllTopic();

    /**
     * 往topic中发送消息
     *
     * @param topic
     * @param message
     */
    void sendMessage(String topic, String message);
}
