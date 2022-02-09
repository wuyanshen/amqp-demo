package com.lvcoding.amqpdemo;

import com.rabbitmq.client.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

// @SpringBootTest
class AmqpDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    // 生产者
    @Test
    void amqpProducer() throws IOException, TimeoutException {
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
        channel.basicPublish("", "hello", null, "你好，我是消息".getBytes());

        channel.close();
        connection.close();
    }

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
        // 通道对应的队列，不存在自动创建
        channel.queueDeclare("hello", false, false ,false, null);

        // 消费消息
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            @Override // 参数body是从消息队列取出的消息
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body = " + new String(body));
            }
        });

        // channel.close();
        // connection.close();
    }

    // 批量生产消息
    @Test
    void batchProducer() throws IOException, TimeoutException {
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
