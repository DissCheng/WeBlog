package com.disscheng.weblog.service;

import com.disscheng.weblog.dto.ArticleStatisticDTO;
import com.disscheng.weblog.entity.Article;
import com.disscheng.weblog.vo.ArticlePublishStatisticsVO;
import com.disscheng.weblog.vo.StatisticsVO;

import java.time.Year;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface DashboardService {
    /**
     * 获取网站总访问量
     * @return 网站总访问量
     */
    public StatisticsVO getStatistics();

    /**
     * 获取近一年发布文章
     * @return
     */
    public List<ArticleStatisticDTO> getArticleByYear(Year year);

    /**
     * 获取近一周pv量
     * @return
     */
     public LinkedHashMap<String, Long> getPv();
}
