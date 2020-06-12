package com.wzy.risunking.user.controller;

import com.wzy.risunking.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @description: 用户接口
 * @author: Wangzy
 * @create: 2018-10-12 15:38
 **/
@RestController
@CrossOrigin
@RequestMapping(value = "web/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 用户登陆
     * @param userName 用户名
     * @param pwd 密码（加密后的密码）
     * @param verificationCode
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map login(String userName, String pwd, String verificationCode, String timestamp){
        return userService.login(userName, pwd, verificationCode, timestamp);
    }

    /**
     * 注册接口
     * @param userName 注册用户名
     * @param userPwd 注册用户密码
     * @param rUserPwd 注册用户密码-确认
     * @param userMobil 用户手机号
     * @param userAvatar 用户头像
     * @param userRealName 用户真实姓名
     * @param userGender 用户性别
     * @param userEmail 用户邮箱
     * @param userInfo 用户信息描述
     * @param userIdCode 用户身份证号
     * @param userHres 用户个人链接
     * @return 是否注册成功
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Boolean register(String userBirthday,String userName, String userPwd, String rUserPwd, String userMobil,
                            String userAvatar,String userRealName, String userGender, String userEmail,
                            String userInfo,String userIdCode, String userHres){
        return userService.register(userBirthday, userName, userPwd, rUserPwd, userMobil,
                                    userAvatar, userRealName, userGender, userEmail,
                                    userInfo, userIdCode, userHres);
    }

    /**
     * 更新用户信息
     * @param userName
     * @param userMobil
     * @param userAvatar
     * @param userRealName
     * @param userGender
     * @param userEmail
     * @param userInfo
     * @param userIdCode
     * @param userHres
     * @return
     */
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public Boolean updateUserInfo(String userBirthday,String userName, String userMobil,
                           String userAvatar,String userRealName, String userGender, String userEmail,
                           String userInfo,String userIdCode, String userHres, String userloginData,
                           String userLoginCount, String userType, String userId){
        return userService.updateUserInfo( userBirthday, userName, null, null, userMobil,
                 userAvatar, userRealName, userGender, userEmail,
                 userInfo, userIdCode, userHres, userloginData,
                 userLoginCount, userType, userId);
    }

    /**
     * 登陆后修改密码
     * @param oldPwd
     * @param newPwd
     * @param userId
     * @return
     */
    @RequestMapping(value = "/resetPwd",method = RequestMethod.POST)
    public Boolean resetPwd(String oldPwd, String newPwd, String userId){
        return userService.resetPwd(oldPwd, newPwd, userId);
    }
}
