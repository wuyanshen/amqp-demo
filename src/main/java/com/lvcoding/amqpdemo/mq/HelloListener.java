package com.lvcoding.amqpdemo.mq;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description 消息消费者，hello队列
 * @date   2022-02-08 下午5:20
 * @author  wuyanshen
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(value = "hello"))
public class HelloListener {

    @RabbitHandler
    public void hello(String msg) {
        System.out.println("msg = " + msg);
    }
}
