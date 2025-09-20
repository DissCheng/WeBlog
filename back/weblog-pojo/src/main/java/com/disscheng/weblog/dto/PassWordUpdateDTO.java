package com.disscheng.weblog.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PassWordUpdateDTO implements Serializable {
    private String userName;
    private String passWord;
    private String rePassWord;
}
