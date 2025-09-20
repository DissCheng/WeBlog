package com.disscheng.weblog.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TagSearchDTO implements Serializable {
    private String query;
}
