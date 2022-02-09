package com.lvcoding.amqpdemo.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description 生产者
 * @date   2022-01-17 下午5:56
 * @author  wuyanshen
 */
public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {
        batchProducer();
    }

    static void batchProducer() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");

        // 连接对象
        Connection connection = connectionFactory.newConnection();

        // 创建通道
        Channel channel = connection.createChannel();
        // 通道对应的队列，不存在自动创建
        channel.queueDeclare("hello", false, false ,false, null);

        // 发布消息
        for (int i=1;i<=20;i++) {
            channel.basicPublish("", "work", null, ("你好，我是消息" + i).getBytes());
        }


        channel.close();
        connection.close();
    }
}
