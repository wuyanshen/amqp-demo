package com.lvcoding.amqpdemo.direct;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description 描述
 * @date   2022-01-17 下午5:54
 * @author  wuyanshen
 */
public class Consumer1 {
    // 消费者
    public static void main(String[] args) throws IOException, TimeoutException {
        amqpConsumer();
    }

    static void amqpConsumer() throws IOException, TimeoutException {
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
        // 通道对应的队列，不存在自动创建 | String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
        channel.queueDeclare("direct", false, false ,false, null);

        // 消费消息 参数2：true自动确认消息 false手动确认消息 | String queue, boolean autoAck, Consumer callback
        channel.basicConsume("direct", false, new DefaultConsumer(channel) {
            @Override // 参数body是从消息队列取出的消息
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body = " + new String(body));

                //参数1：要确认队列中哪个消息(消息标志)  参数2：是否同时开启多个参数确认
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });

        // channel.close();
        // connection.close();
    }
}
