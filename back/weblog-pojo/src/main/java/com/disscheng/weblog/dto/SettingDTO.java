package com.disscheng.weblog.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SettingDTO implements Serializable {

    private String logo;
    private String name;
    private String author;
    private String introduction;
    private String avatar;
    private String githubHomepage;
    private String csdnHomepage;
    private String giteeHomepage;
    private String zhihuHomepage;
}
