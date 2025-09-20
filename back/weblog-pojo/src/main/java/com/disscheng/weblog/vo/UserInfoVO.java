package com.disscheng.weblog.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoVO implements Serializable {
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userAddress;
}
