package com.wzy.risunking.user.service;

import java.util.Map;

/**
 * @description: 用户service接口
 * @author: Wangzy
 * @create: 2018-10-12 17:54
 **/
public interface UserService {

    /**
     * 登陆接口
     * @param userName 用户登陆名
     * @param pwd 用户登陆密码
     * @param verificationCode 用户登陆验证码
     * @param timestamp 时间戳
     * @return 用户信息
     */
    Map login(String userName, String pwd, String verificationCode, String timestamp);

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
    Boolean register(String userBirthday,String userName, String userPwd, String rUserPwd, String userMobil,
                            String userAvatar,String userRealName, String userGender, String userEmail,
                            String userInfo,String userIdCode, String userHres);

    /**
     * 更新用户信息
     * @param userName
     * @param userPwd
     * @param userSalt
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
    Boolean updateUserInfo(String userBirthday,String userName, String userPwd, String userSalt, String userMobil,
                           String userAvatar,String userRealName, String userGender, String userEmail,
                           String userInfo,String userIdCode, String userHres, String userloginData,
                           String userLoginCount, String userType, String userId);

    /**
     * 登陆后修改密码
     * @param oldPwd
     * @param newPwd
     * @param userId
     * @return
     */
    Boolean resetPwd(String oldPwd, String newPwd, String userId);
}
