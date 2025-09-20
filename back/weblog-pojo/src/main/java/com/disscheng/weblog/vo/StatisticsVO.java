package com.disscheng.weblog.vo;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class StatisticsVO implements Serializable {

    private long articleTotalCount;
    private long categoryTotalCount;
    private long tagTotalCount;
    private long pvTotalCount;
}
