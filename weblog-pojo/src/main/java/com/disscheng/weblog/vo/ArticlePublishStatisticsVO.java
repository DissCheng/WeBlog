package com.disscheng.weblog.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Data
@Builder
public class ArticlePublishStatisticsVO implements Serializable {
    //一年的文章发布统计
    private Map<String, Integer> articlePublishStatistics;
}
