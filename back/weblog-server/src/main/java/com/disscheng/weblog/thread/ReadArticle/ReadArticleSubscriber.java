package com.disscheng.weblog.thread.ReadArticle;

import com.disscheng.weblog.mapper.ArticleMapper;
import com.disscheng.weblog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReadArticleSubscriber implements ApplicationListener<ReadArticleEvent> {


      @Autowired
      private ArticleMapper articleMapper;

      @Override
      @Async("threadPoolTaskExecutor")
      public void onApplicationEvent(ReadArticleEvent event) {
          long articleId = event.getArticleId();
          // 获取当前线程名称
          String threadName = Thread.currentThread().getName();

          log.info("==> threadName: {}", threadName);
          log.info("==> 文章阅读事件消费成功，articleId: {}", articleId);
          articleMapper.updateReadNum(articleId);
          articleMapper.updatePV();
      }
}
