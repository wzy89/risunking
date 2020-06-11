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
  `user_type` tinyint(2) DEFAULT 1 COMMENT '用户类型 0管理员 1一般用户 ',
  `user_realname` varchar(50) DEFAULT NULL COMMENT ' 真实姓名',
  `user_avatar` varchar(255) DEFAULT NULL COMMENT '头像图片',
  `user_gender` tinyint(2) DEFAULT 0 COMMENT '性别 0-未知 1-男 2-女',
  `user_birthday` varchar(20) DEFAULT NULL COMMENT '出生日期',
  `user_mobile` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `user_email` varchar(100) DEFAULT NULL COMMENT '邮箱地址',
  `user_create_date` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `user_status` tinyint(4) DEFAULT NULL COMMENT '用户状态 0-用户未激活 1-用户正常 2-用户被禁用',
  `user_info` varchar(255) DEFAULT NULL COMMENT '用户简介',
  `user_login_count` int(11) DEFAULT 0 COMMENT '用户登陆次数',
  `user_login_date` datetime DEFAULT NULL COMMENT '最近登陆时间',
  `user_idcode` varchar(30) DEFAULT NULL COMMENT '身份证号码',
  `user_hres` varchar(255) DEFAULT NULL COMMENT '个人链接',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- --------------------------------
-- Table structure for user2user --
-- --------------------------------
CREATE TABLE IF NOT EXISTS `user2user` (
  `user2user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户与用户关系id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `2user_id` int(11) DEFAULT NULL COMMENT '关注的用户id',
  PRIMARY KEY (`user2user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与用户的关注关系表';

-- ---------------------------------
-- Table structure for permission --
-- ---------------------------------
CREATE TABLE IF NOT EXISTS `permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `permission_name` varchar(30) NOT NULL COMMENT '权限名称',
  `permission_pid` int(11) DEFAULT 0 COMMENT '父权限id',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限定义表';

-- --------------------------------------
-- Table structure for user2premission --
-- --------------------------------------
CREATE TABLE IF NOT EXISTS `user2premission` (
  `user2premission_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户与权限关系id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`user2premission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与权限的关系表';

-- -------------------------------
--  Table structure for folder  --
-- -------------------------------
CREATE TABLE IF NOT EXISTS `folder` (
  `folder_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文件夹id',
  `folder_name` varchar(50) NOT NULL COMMENT '文件夹名称',
  `folder_pid` int(11) DEFAULT 0 COMMENT '文件夹父文件id',
  `user_id` int(11) DEFAULT NULL COMMENT '文件夹的创建者id',
  PRIMARY KEY (`folder_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件夹表';

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

-- --------------------------------------
--  Table structure for user2resource  --
-- --------------------------------------
CREATE TABLE IF NOT EXISTS `user2resource` (
  `user2resource_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户与权限关系id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `resource_id` int(11) NOT NULL COMMENT '权限id',
  `own_type` tinyint(2) DEFAULT 0 COMMENT '用户资源关系 0拥有 1转发',
  PRIMARY KEY (`user2resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源与用户的关系表';

-- ---------------------------
--  Table structure for tag --
-- ---------------------------
CREATE TABLE IF NOT EXISTS `tag` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'rag id',
  `user_id` int(11) default 0 COMMENT '用户id',
  `tag_name` varchar(50) NOT NULL COMMENT 'rag名称',
  `tag_ref_num` int(11) DEFAULT 0 COMMENT 'rag引用次数',
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签表';

-- -----------------------------------------
--  Table structure for user2resource2tag --
-- -----------------------------------------
CREATE TABLE IF NOT EXISTS `user2resource2tag` (
  `user2resource2tag_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `tag_id` int(11) NOT NULL COMMENT 'rag id',
  `resource_id` int(11) NOT NULL COMMENT '资源id',
  PRIMARY KEY (`user2resource2tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签表';

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
INSERT INTO risunking.time_task (time_task_id,task_name,task_desc,task_expression,task_switch) SELECT 1,'AutoCleanCorpseTask', '删除僵尸用户定时任务', '0 00 23 * * ?', '1' FROM DUAL WHERE NOT EXISTS(SELECT time_task_id FROM time_task WHERE time_task_id = 1);

/*启用外键约束*/
SET FOREIGN_KEY_CHECKS=1;