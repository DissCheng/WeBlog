package com.disscheng.weblog.service.impl;

import com.disscheng.weblog.service.RocketMQService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RocketMQServiceImpl implements RocketMQService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    private static final String topic = "weblog-pv-update-topic";

    @Override
    public boolean updatePV(Long articleId) {
        rocketMQTemplate.syncSend(topic, articleId);
        return true;
    }
}
