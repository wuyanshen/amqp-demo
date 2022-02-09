package com.lvcoding.boot;

import com.lvcoding.amqpdemo.AmqpDemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description 描述
 * @date   2022-02-08 下午5:23
 * @author  wuyanshen
 */
@SpringBootTest(classes = AmqpDemoApplication.class)
public class MqBoot {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // hello
    @Test
    public void hello() {
        rabbitTemplate.convertAndSend("hello", "你好rabbitmq");
    }

    // worker模型
    @Test
    public void worker() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("worker", "worker模型"+i );
        }
    }

    // 广播fanout模式
    @Test
    public void fanout() {
        rabbitTemplate.convertAndSend("logs", "", "fanout模型");
    }

    // 订阅topic模式 动态路由
    @Test
    public void topic() {
        rabbitTemplate.convertAndSend("topics", "user.aa.bb.cc", "topic模型");
    }
}
