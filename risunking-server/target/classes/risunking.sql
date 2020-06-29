/*
Target Server Type    : MYSQL
Target Server Version : 50524

Date: 2018-10-12 17:24:10
*/

CREATE DATABASE IF NOT EXISTS risunking DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE risunking;

/*禁用外键约束*/
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user --
-- ----------------------------
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `user_pwd` varchar(20) DEFAULT NULL COMMENT '用户密码',
  `user_salt` varchar(10) DEFAULT NULL COMMENT '加密串',
  `user_type` tinyint(2) DEFAULT 1 COMMENT '用户类型 0管理员 1一般用户 2拉黑用户',
  `user_realname` varchar(50) DEFAULT NULL COMMENT ' 真实姓名',
  `user_avatar` varchar(255) DEFAULT NULL COMMENT '头像图片',
  `user_gender` tinyint(2) DEFAULT 0 COMMENT '性别 0-未知 1-男 2-女',
  `user_mobile` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `user_email` varchar(100) DEFAULT NULL COMMENT '邮箱地址',
  `user_create_date` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `user_login_date` datetime DEFAULT NULL COMMENT '最近登陆时间',
  `user_hres` varchar(255) DEFAULT NULL COMMENT '个人链接',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- -------------------------------
-- Table structure for resource --
-- -------------------------------
CREATE TABLE IF NOT EXISTS `resource` (
  `resource_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `resource_name` varchar(50) DEFAULT NULL COMMENT '资源名',
  `resource_type` tinyint(2) DEFAULT 0 COMMENT '资源类型 0文本 1图片 2Office 3视频 4音频 5pdf -1未知类型',
  `resource_path` varchar(255) DEFAULT NULL COMMENT '资源相对地址(本地文件包含该字段)',
  `resource_href` varchar(255) DEFAULT NULL COMMENT '资源链接(外链接文件包含该字段)',
  `folder_id` int(11) default 0 COMMENT '文件夹id',
  `resource_desc` varchar(255) DEFAULT NULL COMMENT '资源简介',
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';

-- ----------------------------
-- Table structure for daily_word
-- ----------------------------
CREATE TABLE IF NOT EXISTS `daily_word`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trd_id` bigint(20) NULL DEFAULT NULL,
  `src` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `text` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `day` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------------------
-- Table structure for time_task(quartz) --
-- ----------------------------------------
CREATE TABLE IF NOT EXISTS `time_task` (
  `time_task_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_name` varchar(255) NOT NULL COMMENT '任务方法名',
  `task_desc` varchar(255) DEFAULT NULL COMMENT '任务描述',
  `task_expression` varchar(255) NOT NULL COMMENT '定时任务时间配置',
  `task_switch` varchar(2) DEFAULT '1' COMMENT '定时任务开关，1：启用；0：关闭',
  PRIMARY KEY (`time_task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
INSERT INTO time_task (time_task_id,task_name,task_desc,task_expression,task_switch) SELECT 1,'AutoCleanCorpseTask', '删除僵尸用户定时任务', '0 00 23 * * ?', '1' FROM DUAL WHERE NOT EXISTS(SELECT time_task_id FROM time_task WHERE time_task_id = 1);
INSERT INTO time_task (time_task_id,task_name,task_desc,task_expression,task_switch) SELECT 2,'GetDailyWordTask', '获取每日一言定时任务', '0 00 00 * * ?', '1' FROM DUAL WHERE NOT EXISTS(SELECT time_task_id FROM time_task WHERE time_task_id = 2);

/*启用外键约束*/
SET FOREIGN_KEY_CHECKS=1;