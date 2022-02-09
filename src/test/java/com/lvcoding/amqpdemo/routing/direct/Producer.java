package com.lvcoding.amqpdemo.routing.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description 描述
 * @date   2022-01-18 上午9:12
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

        // 声明交换机 参数1：交换机名称 参数2：交换机类型 路由模式
        channel.exchangeDeclare("logs_direct", "direct");

        // 发布消息
        String routingKey = "info";
        for (int i=1;i<=20;i++) {
            // String exchange, String routingKey, BasicProperties props, byte[] body
            channel.basicPublish("logs_direct", routingKey, null, ("我是direct模型发布的基于routingKey:"+ routingKey+"发送的消息" + i).getBytes());
        }


        channel.close();
        connection.close();
    }
}
