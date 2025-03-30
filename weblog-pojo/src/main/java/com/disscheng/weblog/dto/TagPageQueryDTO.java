package com.disscheng.weblog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TagPageQueryDTO implements Serializable {
    /**
     *
     *"current":1, // 要查询的页码
     *"size":10, // 每页要展示的数据量
     *"name":"", // 分类名称
     *"startDate":"", // 起始创建时间
     *"endDate":"", // 结束创建时间
     */
    private int current;
    private int size;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;
}
