package com.disscheng.weblog.controller.users;


import com.disscheng.weblog.dto.ArticleStatisticDTO;
import com.disscheng.weblog.entity.Article;
import com.disscheng.weblog.result.Result;
import com.disscheng.weblog.service.ArticleService;
import com.disscheng.weblog.service.DashboardService;
import com.disscheng.weblog.vo.ArticlePublishStatisticsVO;
import com.disscheng.weblog.vo.StatisticsPvVO;
import com.disscheng.weblog.vo.StatisticsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;

@RestController
@RequestMapping("users/dashboard")
@Slf4j
public class DashBoardController {

    @Autowired
    private DashboardService dashboardService;

    /**
     * 获取统计信息
     * @return
     */
    @GetMapping("/statistics")
    public Result<StatisticsVO> getStatistics() {
        return Result.success(dashboardService.getStatistics());
    }
    /**
     * 获取文章年发布统计
     * @return
     */
    @GetMapping("/publishArticle/statistics")
    public Result<Map<String, Integer>> getArticleStatistics() {
        log.info("获取文章年发布统计");
        HashMap<String, Integer> map = new HashMap<>();
        List<ArticleStatisticDTO> articleStatisticDTOList = dashboardService.getArticleByYear(Year.now());
        // 遍历从一年前的今天到今天的每一天
        LocalDate  oneYearAgo = LocalDate.now().minusYears(1);
        LocalDate  today = LocalDate.now();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (ArticleStatisticDTO articleStatisticDTO : articleStatisticDTOList) {
            map.put(simpleDateFormat.format(articleStatisticDTO.getCreateDate()), articleStatisticDTO.getCount());
        }
        for (LocalDate date = oneYearAgo; !date.isAfter(today); date = date.plusDays(1)) {
            String dateStr = date.toString();
            if(!map.containsKey(dateStr)){map.put(dateStr, 0);}
        }
        return Result.success(map);
    }


    /**
     * 近一周的PV统计
     *
     */
    @GetMapping("/pv/statistics")
    public Result<StatisticsPvVO> getPvStatistics() {
        log.info("获取PV统计");
        LinkedHashMap<String, Long> map = dashboardService.getPv();
        StatisticsPvVO statisticsPvVO = StatisticsPvVO.builder()
                .pvDates(new ArrayList<>())
                .pvCounts(new ArrayList<>())
                .build();
        for(String dateStr : map.keySet()){
            statisticsPvVO.getPvDates().add(dateStr);
            statisticsPvVO.getPvCounts().add(map.get(dateStr));
        }
        log.info("获取PV统计结果："+map.toString());
        return Result.success(statisticsPvVO);
    }
}
