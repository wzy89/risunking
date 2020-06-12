package com.wzy.risunking.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * @description: 用户数据库操作
 * @author: Wangzy
 * @create: 2018-10-12 17:39
 **/
@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 根据用户名查询用户信息
     *
     * @param userName 用户名（登陆用户名）
     * @return
     */
    public Map getUserInfoByUserName(String userName) {
        String sql = "SELECT " +
                "user_id AS userId " +
                "user_name AS userName, " +
                "user_pwd AS userPwd, " +
                "user_salt AS userSalt, " +
                "user_avatar AS userAvatar, " +
                "user_birthday AS userBirthday, " +
                "user_type AS userType, " +
                "user_realname AS userRealName, " +
                "user_gender AS userGender, " +
                "user_mobile AS userMobil, " +
                "user_email AS userEmail, " +
                "user_info AS userInfo, " +
                "user_login_count AS userLoginCount, " +
                "user_idcode AS userIdCode, " +
                "user_hres AS userHres " +
                "FROM " +
                "risunking. USER " +
                "WHERE " +
                "user_name = '" + userName + "' ";
        List userInfos = jdbcTemplate.queryForList(sql);
        if (userInfos.size() == 1){
            return (Map) userInfos.get(0);
        }
        return null;
    }

    /**
     * 根据userId获取用户信息
     *
     * @param userId
     * @return
     */
    public Map getUserInfoByUserId(String userId) {
        String sql = "SELECT " +
                "user_id AS userId " +
                "user_name AS userName, " +
                "user_pwd AS userPwd, " +
                "user_salt AS userSalt, " +
                "user_avatar AS userAvatar, " +
                "user_birthday AS userBirthday, " +
                "user_type AS userType, " +
                "user_realname AS userRealName, " +
                "user_gender AS userGender, " +
                "user_mobile AS userMobil, " +
                "user_email AS userEmail, " +
                "user_info AS userInfo, " +
                "user_login_count AS userLoginCount, " +
                "user_idcode AS userIdCode, " +
                "user_hres AS userHres " +
                "FROM " +
                "risunking. USER " +
                "WHERE " +
                "user_id = '" + userId + "' ";
        List userInfos = jdbcTemplate.queryForList(sql);
        if (userInfos.size() == 1){
            return (Map) userInfos.get(0);
        }
        return null;
    }

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
     * @param userLoginCount
     * @param userLoginDate
     * @return
     */
    public int updateUserInfo(String userName, String userPwd, String userSalt, String userMobil,
                        String userAvatar,String userRealName, String userGender, String userEmail,
                        String userInfo,String userIdCode, String userHres, String userLoginCount,
                        String userLoginDate,String userType, String userId) {
        String sql = "UPDATE risunking.user SET ";
        if (userName != null && !"".equals(userName)){
            sql = sql + "user_name = '" + userName + "', ";
        }
        if (userType != null && !"".equals(userType)){
            sql = sql + "user_type = " + Integer.valueOf(userType) + ", ";
        }
        if (userPwd != null && !"".equals(userPwd)){
            sql = sql + "user_pwd = '" + userPwd + "', ";
        }
        if (userSalt != null && !"".equals(userSalt)){
            sql = sql + "user_salt = '" + userSalt + "', ";
        }
        if (userMobil != null && !"".equals(userMobil)){
            sql = sql + "user_mobile = '" + userMobil + "', ";
        }
        if (userAvatar != null && !"".equals(userAvatar)){
            sql = sql + "user_avatar = '" + userAvatar + "', ";
        }
        if (userRealName != null && !"".equals(userRealName)){
            sql = sql + "user_realname = '" + userRealName + "', ";
        }
        if (userGender != null && !"".equals(userGender)){
            sql = sql + "user_gender = " + Integer.valueOf(userGender) + ", ";
        }
        if (userEmail != null && !"".equals(userEmail)){
            sql = sql + "user_email = '" + userEmail + "', ";
        }
        if (userInfo != null && !"".equals(userInfo)){
            sql = sql + "user_info = '" + userInfo + "', ";
        }
        if (userIdCode != null && !"".equals(userIdCode)){
            sql = sql + "user_idcode = '" + userIdCode + "', ";
        }
        if (userHres != null && !"".equals(userHres)){
            sql = sql + "user_hres = '" + userHres + "', ";
        }
        if (userLoginCount != null && !"".equals(userLoginCount)){
            sql = sql + "user_login_count = user_login_count+1, ";
        }
        if (userLoginDate != null && !"".equals(userLoginDate)){
            sql = sql + "user_login_date = '" + userLoginDate + "', ";
        }
        if (sql.contains(",")){
            sql = sql.substring(0,sql.length()-2);
            sql = sql + " where user_id = '"+userId + "'";
            return jdbcTemplate.update(sql);
        }else {
            return 0;
        }
    }

    /**
     * 更新用户密码
     * @param oldPwd
     * @param newPwd
     * @param userId
     * @return
     */
    public int updateUserPwd(String oldPwd, String newPwd, String userId) {
        String sql = "UPDATE risunking.user SET user_pwd = '"+ newPwd
                +"' where user_id = '"+userId+"' and user_pwd = '" + oldPwd +"'";
        return jdbcTemplate.update(sql);
    }

    /**
     * 添加用户（注册用户）
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
    public int addUser(String userBirthday,String userName, String userPwd, String userSalt, String userMobil,
                       String userAvatar,String userRealName, String userGender, String userEmail,
                       String userInfo,String userIdCode, String userHres, String userLoginCount, String updateTime) {
        if (userGender == null || userGender.equals("")){
            userGender = "0";
        }
        String sql =
                "INSERT INTO risunking.user ( " +
                "user_birthday, " +
                "user_info, " +
                "user_login_count, " +
                "user_hres, " +
                "user_pwd, " +
                "user_salt, " +
                "user_mobile, " +
                "user_login_date, " +
                "user_idcode, " +
                "user_name, " +
                "user_avatar, " +
                "user_email, " +
                "user_realname, " +
                "user_gender " +
                ") " +
                "VALUES " +
                "( " +
                "'" + userBirthday + "', " +
                "'"+userInfo+"', " +
                "'" + userLoginCount + "', " +
                "'" + userHres + "', " +
                "'" + userPwd + "', " +
                "'" + userSalt + "', " +
                "'" + userMobil + "', " +
                "'" + updateTime + "', " +
                "'" + userIdCode + "', " +
                "'" + userName + "', " +
                "'" + userAvatar + "', " +
                "'" + userEmail + "', " +
                "'" + userRealName + "', " +
                "'" + userGender + "')";
        return jdbcTemplate.update(sql);
    }
}
