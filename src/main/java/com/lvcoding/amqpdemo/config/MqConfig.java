package com.lvcoding.amqpdemo.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description 描述
 * @date   2022-02-10 下午6:05
 * @author  wuyanshen
 */
@Configuration
public class MqConfig {

    /**
     * 将消息转化成json格式
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
