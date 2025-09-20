package com.disscheng.weblog.properties;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class RabbitMQProperties {
    /**
     * 队列主题名称
     */
    public static final String RABBITMQ_TOPIC = "RABBITMQ_TOPIC";

    /**
     *
     * DIRECT交换机名称
     */
    public static final String RABBITMQ_DIRECT_EXCHANGE = "RABBITMQ_DIRECT_EXCHANGE";

    /**
     *
     * DIRECT交换机和队列绑定的匹配值
     */
    public static final String RABBITMQ_DIRECT_ROUTING = "RABBITMQ_DIRECT_ROUTING";
}
