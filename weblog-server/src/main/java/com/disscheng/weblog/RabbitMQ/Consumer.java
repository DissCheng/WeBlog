package com.disscheng.weblog.RabbitMQ;

import com.disscheng.weblog.properties.RabbitMQProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;
/*
@Component
@RabbitListener(queues= RabbitMQProperties.RABBITMQ_TOPIC)
public class Consumer {
    @RabbitHandler
    public void process(Map map) {
        System.out.println("消费者消费了一条消息："+map.toString());
    }
}
*/