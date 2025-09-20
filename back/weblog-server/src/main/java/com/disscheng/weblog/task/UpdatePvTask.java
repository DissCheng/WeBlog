package com.disscheng.weblog.task;

import com.disscheng.weblog.mapper.ArticleMapper;
import com.disscheng.weblog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UpdatePvTask {

    @Autowired
    private ArticleMapper articleMapper;


    @Scheduled(cron = "0 0 23 * * ?", zone = "Asia/Shanghai")
    public void execute() {
        articleMapper.insertArticlePvStatistics();
    }
}
