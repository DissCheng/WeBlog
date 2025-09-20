package com.disscheng.weblog.vo;


import com.disscheng.weblog.entity.Tag;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TagPageQueryVO implements Serializable {
    private long total;
    private int current;
    private int size;
    private int pages;
    private List<Tag> data;
}
