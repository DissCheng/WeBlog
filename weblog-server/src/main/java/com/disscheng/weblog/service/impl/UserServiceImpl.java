package com.disscheng.weblog.service.impl;

import com.disscheng.weblog.constant.MessageConstant;
import com.disscheng.weblog.context.BaseContext;
import com.disscheng.weblog.dto.PassWordUpdateDTO;
import com.disscheng.weblog.dto.UserLoginDTO;
import com.disscheng.weblog.entity.User;
import com.disscheng.weblog.exception.AccountNotFoundException;
import com.disscheng.weblog.exception.AccountPasswordException;
import com.disscheng.weblog.mapper.UserMapper;
import com.disscheng.weblog.service.UserService;
import com.disscheng.weblog.vo.UserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 登录
     * @param userLoginDTO
     * @return
     */
    public User login(UserLoginDTO userLoginDTO) {
        User user=userMapper.selectByUsername(userLoginDTO.getUsername());
        if(user==null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        if(!user.getPassWord().equals(userLoginDTO.getPassword())){
            throw new AccountPasswordException(MessageConstant.PASSWORD_ERROR);
        }
        return user;
    }
    /**
     * 获取用户信息
     * @return
     */
    public UserInfoVO getUserInfo() {

        User user=userMapper.selectById(BaseContext.getUserId());
        log.info("user:{}",user);
        UserInfoVO userInfoVO=new UserInfoVO();
        BeanUtils.copyProperties(user,userInfoVO);
        return userInfoVO;
    }
    /**
     * 修改密码
     * @param passWordUpdateDTO
     * @return
     */
    public boolean updatePassword(PassWordUpdateDTO passWordUpdateDTO) {
        User user = userMapper.selectById(BaseContext.getUserId());
        if (user == null) {
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        System.out.println(passWordUpdateDTO);
        log.info("user:{}",passWordUpdateDTO.getPassWord());
        user.setPassWord(passWordUpdateDTO.getPassWord());
        userMapper.updateById(user);
        return true;
    }
}
