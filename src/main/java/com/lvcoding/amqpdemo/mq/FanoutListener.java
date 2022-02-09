package com.lvcoding.amqpdemo.mq;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description 消息消费者，worker队列
 * @date   2022-02-08 下午5:20
 * @author  wuyanshen
 */
@Component
public class FanoutListener {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 创建临时队列
                    exchange = @Exchange(value = "logs", type = "fanout") // 绑定交换机
            )
    })
    public void fanout1(String msg) {
        System.out.println("fanout1 = " + msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 创建临时队列
                    exchange = @Exchange(value = "logs", type = "fanout") // 绑定交换机
            )
    })
    public void fanout2(String msg) {
        System.out.println("fanout2 = " + msg);
    }

}
