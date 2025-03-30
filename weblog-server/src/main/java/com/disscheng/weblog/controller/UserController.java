package com.disscheng.weblog.controller;

import com.disscheng.weblog.constant.JwtClaimsConstant;
import com.disscheng.weblog.dto.PassWordUpdateDTO;
import com.disscheng.weblog.dto.UserLoginDTO;
import com.disscheng.weblog.entity.User;
import com.disscheng.weblog.properties.JwtProperties;
import com.disscheng.weblog.result.Result;
import com.disscheng.weblog.service.UserService;
import com.disscheng.weblog.utils.JwtUtil;
import com.disscheng.weblog.vo.UserInfoVO;
import com.disscheng.weblog.vo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController{

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;


    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO user){
        User userEntity=userService.login(user);
        Map<String,Object> claims=new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID,userEntity.getId());
        //登录成功返回token
        String token = JwtUtil.createJwt(jwtProperties.getSecretKey(), jwtProperties.getTtl(), claims);
        UserLoginVO userLoginVO = new UserLoginVO();
        userLoginVO.setId(userEntity.getId());
        userLoginVO.setUserName(user.getUsername());
        userLoginVO.setJwtToken(token);
        return Result.success(userLoginVO);
    }


    @PostMapping("/info")
    public Result<UserInfoVO> info(){
        log.info("获取用户信息");
        return Result.success(userService.getUserInfo());
    }


    @PostMapping("/password/update")
    public Result<String> updatePassword(@RequestBody PassWordUpdateDTO passWordUpdateDTO){
        log.info("更新密码");
        userService.updatePassword(passWordUpdateDTO);
        return Result.success();
    }
    @GetMapping("/test")
    public Result<String> test(){
        return Result.success("This is test");
    }
}