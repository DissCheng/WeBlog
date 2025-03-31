package com.disscheng.weblog.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TagGetDTO implements Serializable {
    private List<Long> tagIds;
}
