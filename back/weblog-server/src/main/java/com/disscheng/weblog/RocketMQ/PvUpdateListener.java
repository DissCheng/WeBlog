package com.disscheng.weblog.RocketMQ;


import com.disscheng.weblog.mapper.ArticleMapper;
import com.github.phantomthief.collection.BufferTrigger;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

@Component
@RocketMQMessageListener(topic = "weblog-pv-update-topic", consumerGroup = "weblog-pv-update-group")
public class PvUpdateListener implements RocketMQListener<Long> {
    @Autowired
    private ArticleMapper articleMapper;

    // ① 创建聚合器：缓存 5k 条，每批 1k 条，最长 1 秒
    private final BufferTrigger<Long> buffer = BufferTrigger.<Long>batchBlocking()
            .bufferSize(5000)
            .batchSize(1000)
            .linger(Duration.ofSeconds(1))
            .setConsumerEx(this::batchSave)
            .build();

    public void onMessage(Long message) {
        buffer.enqueue(message);
    }

    /** 批量消费回调（异步） */
    private void batchSave(List<Long> batch){
        HashMap<Long,Long> hm = new HashMap<>();
        batch.forEach(batchId -> {
            if(hm.containsKey(batchId)){
                hm.put(batchId,hm.get(batchId) + 1);
            }else{
                hm.put(batchId,1L);
            }
        });
        for(Long id : hm.keySet()){
            articleMapper.batchUpdateReadNum(id,hm.get(id));
        }
    }
}
