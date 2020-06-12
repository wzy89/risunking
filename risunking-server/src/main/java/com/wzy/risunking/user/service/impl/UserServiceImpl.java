package com.wzy.risunking.user.service.impl;

import com.wzy.risunking.global.entity.Response;
import com.wzy.risunking.user.dao.UserDao;
import com.wzy.risunking.global.entity.CommandException;
import com.wzy.risunking.user.service.UserService;
import com.wzy.risunking.utils.DataCheck;
import com.wzy.risunking.utils.DateTimeUtil;
import com.wzy.risunking.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;

/**
 * @description: 用户service
 * @author: Wangzy
 * @create: 2018-10-12 17:53
 **/

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    /**
     * 登陆接口
     * @param userName 用户登陆名
     * @param pwd 用户登陆密码
     * @param verificationCode 用户登陆验证码
     * @param timestamp 时间戳
     * @return 用户信息
     */
    @Override
    public Map login(String userName, String pwd, String verificationCode, String timestamp){
        // 请求参数是否错误
        if (DataCheck.containsEmptyString(userName,pwd,timestamp)){
            throw new CommandException(Response.PARAM_ERROR, "请求参数错误");
        }
        // 接口时间是否超时
        long currentSecTime = DateTimeUtil.getCurrentDateTimestamp()/1000;
        long requestSecTime = Long.valueOf(timestamp);
        if (currentSecTime - requestSecTime > 30 || currentSecTime - requestSecTime <= -30){
            throw new CommandException(Response.PARAM_ERROR, "接口请求超时");
        }
        // 密码是否正确
        Map userInfo = userDao.getUserInfoByUserName(userName);
        if (userInfo == null){
            throw new CommandException(Response.PARAM_ERROR, "没有找到该用户");
        }
        String _pwd = userInfo.get("userPwd").toString();
        //前端也需要除以一个71，这是当作一个默认的参数，另外盐值也可以加一部分运算 todo 这里没处理
        _pwd = _pwd + String.valueOf(Long.valueOf(requestSecTime/71)) + userInfo.get("userSalt");
        try {
            _pwd = new MD5(_pwd).get16();
        } catch (Exception e){
            throw new CommandException(Response.INNER_ERROR, "MD5编码错误");
        }
        if (!_pwd.equals(pwd)){
           throw new CommandException(Response.INNER_ERROR, "用户密码错误");
        }else {
            //更新用户登陆时间和登陆次数
            userDao.updateUserInfo(null,null,null,null,
                    null,null,null,null,
                    null,null,null, "1",
                    DateTimeUtil.getCurrentDateTimeString("yyyy-MM-DD HH:mm.ss"),
                    null,userInfo.get("userId").toString());
            //清除用户密码
            userInfo.remove("userPwd");
            // todo 这里其实还需要在redis里面存一个token，这里暂时没做这部分
            // ...
            return userInfo;
        }
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
    @Override
    public Boolean register(String userBirthday,String userName, String userPwd, String rUserPwd, String userMobil,
                        String userAvatar,String userRealName, String userGender, String userEmail,
                        String userInfo,String userIdCode, String userHres){
        // 请求参数是否完整
        if (DataCheck.containsEmptyString(userName,userPwd,rUserPwd, userMobil, userEmail)){
            throw new CommandException(Response.PARAM_ERROR, "请求参数错误");
        }
        // 两次输入的密码是否一样
        if (!userPwd.equals(rUserPwd)){
            throw new CommandException(Response.PARAM_ERROR, "两次输入的密码不一样");
        }
        // 插入数据库
        String userSalt = getStringRandom(8);// 盐值
        userPwd = new MD5(rUserPwd).get16();        // MD5加密（加密也没用盐值😄）
        int num = userDao.addUser(userBirthday,userName, userPwd, userSalt, userMobil,
                                  userAvatar, userRealName, userGender, userEmail,
                                  userInfo, userIdCode, userHres, "0",
                                  DateTimeUtil.getCurrentDateTimeString("yyyy-MM-DD HH:mm.ss"));
        if (num >= 1){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 更新用户信息
     * @param userName      用户名
     * @param userPwd       密码
     * @param userSalt      盐值
     * @param userMobil     手机
     * @param userAvatar    头像
     * @param userRealName  真实姓名
     * @param userGender    性别
     * @param userEmail     邮箱
     * @param userInfo      描述
     * @param userIdCode    身份证号
     * @param userHres      个人连接
     * @param userId        用户id
     * @return
     */
    @Override
    public Boolean updateUserInfo(String userBirthday,String userName, String userPwd, String userSalt, String userMobil,
                       String userAvatar,String userRealName, String userGender, String userEmail,
                       String userInfo,String userIdCode, String userHres, String userloginData,
                       String userLoginCount, String userType, String userId){
        // 请求参数是否完整
        if (DataCheck.containsEmptyString(userId)){
            throw new CommandException(Response.PARAM_ERROR, "请求参数错误");
        }
        int num  = userDao.updateUserInfo(userName,userPwd,userSalt,userMobil,
                userAvatar,userRealName,userGender,userEmail,
                userInfo,userIdCode,userHres,userLoginCount,
                userloginData,userType,userId);
        if (num == 1){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 登陆后修改密码
     *
     * @param oldPwd
     * @param newPwd
     * @param userId
     * @return
     */
    @Override
    public Boolean resetPwd(String oldPwd, String newPwd, String userId){
        // 请求参数是否完整
        if (DataCheck.containsEmptyString(userId, oldPwd, newPwd)){
            throw new CommandException(Response.PARAM_ERROR, "请求参数错误");
        }
        int num = userDao.updateUserPwd(oldPwd,newPwd,userId);
        if (num == 1){
            return true;
        }else {
            return false;
        }
    }
    /**
     * 生成随机数字和字母的盐值（其实也就混淆一些，盐值根本就没被使用）
     * @param length 随机生成几位
     * @return
     */
    private static String getStringRandom(int length) {
        String val = "";
        Random random = new Random();
        for(int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
}
