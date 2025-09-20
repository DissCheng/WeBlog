package com.disscheng.weblog.service;

import com.disscheng.weblog.dto.PassWordUpdateDTO;
import com.disscheng.weblog.dto.UserLoginDTO;
import com.disscheng.weblog.entity.User;
import com.disscheng.weblog.vo.UserInfoVO;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {

    /**
     * 登录
     * @param userLoginDTO 用户登录信息
     * @return 用户信息
     */
    public User login(UserLoginDTO userLoginDTO);
    /**
     * 注册
     * @param userLoginDTO 用户注册信息
     * @return 用户信息
     */
    public User register(UserLoginDTO userLoginDTO);
    /**
     * 获取用户信息
     * @return 用户信息
     */
    public UserInfoVO getUserInfo();

    /**
     * 修改密码
     * @param passWordUpdateDTO 密码信息
     * @return 是否修改成功
     */
    public boolean updatePassword(PassWordUpdateDTO passWordUpdateDTO);

    /**
     * 获取权限
     *
     */
    public boolean getPermission();
}
