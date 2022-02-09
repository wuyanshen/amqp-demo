package com.lvcoding.amqpdemo.mq;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description 消息消费者，worker队列
 * @date   2022-02-08 下午5:20
 * @author  wuyanshen
 */
@Component
public class WorkListener {

    @RabbitListener(queuesToDeclare = @Queue(value = "worker"))
    public void worker1(String msg) {
        System.out.println("worker1 = " + msg);
    }

    @RabbitListener(queuesToDeclare = @Queue(value = "worker"))
    public void worker2(String msg) {
        System.out.println("worker2 = " + msg);
    }
}
