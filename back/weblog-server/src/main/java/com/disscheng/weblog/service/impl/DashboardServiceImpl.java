package com.disscheng.weblog.service.impl;


import com.disscheng.weblog.dto.ArticleStatisticDTO;
import com.disscheng.weblog.entity.Article;
import com.disscheng.weblog.mapper.ArticleMapper;
import com.disscheng.weblog.mapper.CategoryMapper;
import com.disscheng.weblog.mapper.TagMapper;
import com.disscheng.weblog.service.DashboardService;
import com.disscheng.weblog.vo.ArticlePublishStatisticsVO;
import com.disscheng.weblog.vo.StatisticsVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.time.Year;

import static java.time.LocalDate.now;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private TagMapper tagMapper;

    /*
     * 获取访问量和文章数
     */
    public StatisticsVO getStatistics() {
        StatisticsVO statisticsVO = StatisticsVO.builder()
                .articleTotalCount(articleMapper.count())
                .categoryTotalCount(categoryMapper.count())
                .tagTotalCount(tagMapper.count())
                .pvTotalCount(articleMapper.getTotalReadNum())
                .build();
        return statisticsVO;
    }

    /**
     * 获取今年发布文章
     * @return
     */
    public List<ArticleStatisticDTO> getArticleByYear(Year year) {
        LocalDateTime startDate = LocalDate.now().minusYears(1).atStartOfDay(); // 去年今天 00:00
        LocalDateTime endDate   = LocalDateTime.now();                          // 此刻     // 现在（2025-06-25 14:30...）
        List<ArticleStatisticDTO> statisticDTOList=articleMapper.getArticleByDate(startDate, endDate);
        ArticlePublishStatisticsVO articlePublishStatisticsVO = ArticlePublishStatisticsVO.builder()
                .articlePublishStatistics(new java.util.HashMap<>())
               .build();
        statisticDTOList.forEach((articleStatisticDTO)->{
            articlePublishStatisticsVO.getArticlePublishStatistics().putIfAbsent(
                    articleStatisticDTO.getCreateDate().toString(),
                    articleStatisticDTO.getCount()
            );
        });
        return statisticDTOList;
    }

    /**
     * 获取近一周pv
     * @return
     */
    public LinkedHashMap<String, Long> getPv() {
        LinkedHashMap<String, Long> map = new LinkedHashMap<>();
        // 遍历从一周前的今天到今天的每一天
        LocalDate oneWeekAgo = LocalDate.now().minusWeeks(1);
        LocalDate  today = LocalDate.now();
        for (LocalDate date = oneWeekAgo; !date.isAfter(today); date = date.plusDays(1)) {
            String dateStr = date.toString();
            Long pv = articleMapper.getPV(date);
            if (pv == null) {
                map.put(dateStr, 0L);
            } else {
                map.put(dateStr, pv);
            }
        }
        return map;
    }
}
