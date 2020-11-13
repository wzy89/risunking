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
-- Table structure for daily_word
-- ----------------------------
CREATE TABLE IF NOT EXISTS `daily_word`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trd_id` bigint(20) NULL DEFAULT NULL,
  `src` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `text` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `day` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `trdid_index`(`trd_id`) USING BTREE,
  INDEX `day_index`(`day`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for global_code
-- ----------------------------
CREATE TABLE IF NOT EXISTS `global_code`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编码',
  `name` varchar(63) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型',
  `status` tinyint(2) NOT NULL DEFAULT 1 COMMENT '状态：默认1开启，0关闭',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `type_index`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for guests
-- ----------------------------
CREATE TABLE IF NOT EXISTS `guests`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '留言id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '留言内容',
  `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '留言者昵称',
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '留言者邮箱',
  `create_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `pid` int(11) NOT NULL DEFAULT 0 COMMENT '父留言id',
  `expand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '留言扩展',
  `public` tinyint(2) NOT NULL DEFAULT 0 COMMENT '是否公开',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '留言表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hots
-- ----------------------------
CREATE TABLE IF NOT EXISTS `hots`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `top_id` int(11) NOT NULL DEFAULT 0 COMMENT '排名最高的资源id',
  `type_code` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '资源类型编码',
  `hot` float(255, 0) NOT NULL DEFAULT 0 COMMENT '热度值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
CREATE TABLE IF NOT EXISTS `resource`  (
  `resource_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `resource_cover` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '封面图片path',
  `resource_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '资源名',
  `resource_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '资源类型',
  `resource_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '资源相对地址(本地文件包含该字段)',
  `resource_href` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '资源链接(外链接文件包含该字段)',
  `resource_owner` int(11) NOT NULL DEFAULT 1 COMMENT '资源拥有者（上传者）',
  `resource_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '资源简介',
  `update_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `expand` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '扩展字段',
  PRIMARY KEY (`resource_id`) USING BTREE,
  INDEX `resource_type_index`(`resource_type`) USING BTREE,
  INDEX `resource_owner_index`(`resource_owner`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for resource_to_tags
-- ----------------------------
CREATE TABLE IF NOT EXISTS `resource_to_tags`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `resource_id` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `to_resource_id_index`(`resource_id`) USING BTREE,
  INDEX `to_tag_code_index`(`tag_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tags
-- ----------------------------
CREATE TABLE IF NOT EXISTS `tags`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tag_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '标签名',
  `tag_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '标签编码',
  `status` tinyint(2) NOT NULL DEFAULT 1 COMMENT '标签状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `tag_code_index`(`tag_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for time_task
-- ----------------------------
CREATE TABLE IF NOT EXISTS `time_task`  (
  `time_task_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务方法名',
  `task_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务描述',
  `task_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '定时任务时间配置',
  `task_switch` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '定时任务开关，1：启用；0：关闭',
  PRIMARY KEY (`time_task_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
INSERT INTO `risunking`.`time_task`(`time_task_id`, `task_name`, `task_desc`, `task_expression`, `task_switch`) VALUES (1, 'AutoCleanCorpseTask', '删除僵尸用户定时任务', '0 0 23 * * ?', '0') on duplicate key update  `time_task_id`='1';
INSERT INTO `risunking`.`time_task`(`time_task_id`, `task_name`, `task_desc`, `task_expression`, `task_switch`) VALUES (2, 'GetDailyWordTask', '获取每日一言定时任务', '0 0 0 * * ?', '1') on duplicate key update `time_task_id`='2';

-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE IF NOT EXISTS `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `user_pwd` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `user_salt` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '加密串',
  `user_type` tinyint(2) NOT NULL DEFAULT 1 COMMENT '用户类型 0管理员 1一般用户 2拉黑用户',
  `user_realname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT ' 真实姓名',
  `user_avatar` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '头像图片',
  `user_gender` tinyint(2) NOT NULL DEFAULT 0 COMMENT '性别 0-未知 1-男 2-女',
  `user_mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '手机号码',
  `user_email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '邮箱地址',
  `user_create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `user_login_date` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近登陆时间',
  `user_hres` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '个人链接',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

/*启用外键约束*/
SET FOREIGN_KEY_CHECKS=1;