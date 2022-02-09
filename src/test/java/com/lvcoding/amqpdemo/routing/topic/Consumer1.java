package com.lvcoding.amqpdemo.routing.topic;

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
        // 每次只能消费一个消息
        channel.basicQos(1);
        // 声明交换机 | String exchange, String type
        channel.exchangeDeclare("topics", "topic");
        // 声明临时队列
        String queueName = channel.queueDeclare().getQueue();

        // 绑定交换机和队列 | String queue, String exchange, String routingKey
        channel.queueBind(queueName, "topics", "user.*");


        // 消费消息 参数2：true自动确认消息 false手动确认消息 | String queue, boolean autoAck, Consumer callback
        channel.basicConsume(queueName, false, new DefaultConsumer(channel) {
            @Override // 参数body是从消息队列取出的消息
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body = " + new String(body));

                //参数1：要确认队列中哪个消息(消息标志)  参数2：是否同时开启多个参数确认 | long deliveryTag, boolean multiple
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });

        // channel.close();
        // connection.close();
    }
}
