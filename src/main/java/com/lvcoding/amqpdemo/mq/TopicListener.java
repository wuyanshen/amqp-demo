package com.lvcoding.amqpdemo.mq;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description 消息消费者，topic订阅模式
 * @date   2022-02-08 下午5:20
 * @author  wuyanshen
 */
@Component
public class TopicListener {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 创建临时队列
                    exchange = @Exchange(value = "topics", type = "topic"), // 绑定交换机
                    key = {"user.save", "user.*"}
            )
    })
    public void topic1(String msg) {
        System.out.println("topic1 = " + msg);
    }

    // *匹配一个，#匹配一个或多个
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 创建临时队列
                    exchange = @Exchange(value = "topics", type = "topic"), // 绑定交换机
                    key = {"order.#", "product.#", "user.*"}
            )
    })
    public void topic2(String msg) {
        System.out.println("topic2 = " + msg);
    }

}
