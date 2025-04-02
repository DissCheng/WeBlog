package com.disscheng.weblog.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class StatisticsPvVO implements Serializable {
    private List<String> pvDates;
    private List<Long> pvCounts;
}
