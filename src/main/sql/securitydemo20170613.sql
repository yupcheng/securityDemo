/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50541
Source Host           : localhost:3306
Source Database       : securitydemo

Target Server Type    : MYSQL
Target Server Version : 50541
File Encoding         : 65001

Date: 2017-06-13 17:13:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `app_user`
-- ----------------------------
DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sso_id` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `state` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sso_id` (`sso_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_user
-- ----------------------------
INSERT INTO `app_user` VALUES ('1', 'yiibai', '$2a$10$6e2mmsbKPVMRv1zCUTxcS.k2wPxqaXc6.wseLpYBB8qzfIMmKimBK', 'Yiibai', 'Watcher', 'admin@yiibai.com', 'Active');
INSERT INTO `app_user` VALUES ('2', 'danny', '123456', 'Danny', 'Theys', 'danny@xyz.com', 'Active');
INSERT INTO `app_user` VALUES ('3', 'sam', '$2a$10$6e2mmsbKPVMRv1zCUTxcS.k2wPxqaXc6.wseLpYBB8qzfIMmKimBK', 'Sam', 'Smith', 'samy@xyz.com', 'Active');
INSERT INTO `app_user` VALUES ('4', 'nicole', '123456', 'Nicole', 'warner', 'nicloe@xyz.com', 'Active');
INSERT INTO `app_user` VALUES ('5', 'kenny', '123456', 'Kenny', 'Roger', 'kenny@xyz.com', 'Active');
INSERT INTO `app_user` VALUES ('6', 'zs', '$2a$10$DRy5M/hD4a9ieQYTlEAXvei3DmrXjAEyBhR3a/6GvfVkB.7tXI5ya', 'zhang', 'san', '1107674062@qq.com', 'Active');

-- ----------------------------
-- Table structure for `app_user_user_profile`
-- ----------------------------
DROP TABLE IF EXISTS `app_user_user_profile`;
CREATE TABLE `app_user_user_profile` (
  `user_id` bigint(20) NOT NULL,
  `user_profile_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`user_profile_id`),
  KEY `FK_USER_PROFILE` (`user_profile_id`),
  CONSTRAINT `FK_APP_USER` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK_USER_PROFILE` FOREIGN KEY (`user_profile_id`) REFERENCES `user_profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_user_user_profile
-- ----------------------------
INSERT INTO `app_user_user_profile` VALUES ('2', '1');
INSERT INTO `app_user_user_profile` VALUES ('3', '2');
INSERT INTO `app_user_user_profile` VALUES ('5', '2');
INSERT INTO `app_user_user_profile` VALUES ('6', '2');
INSERT INTO `app_user_user_profile` VALUES ('4', '3');
INSERT INTO `app_user_user_profile` VALUES ('5', '3');

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL,
  `parentMenuId` bigint(20) NOT NULL,
  `title` varchar(30) NOT NULL,
  `url` varchar(100) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `maker` varchar(30) NOT NULL,
  `status` smallint(6) NOT NULL DEFAULT '1',
  `sort` smallint(6) NOT NULL DEFAULT '0',
  `level` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `maker` (`maker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '0', '第一个菜单', '/', '2017-06-13 15:54:12', '1001', '1', '1', '1');

-- ----------------------------
-- Table structure for `menu_role`
-- ----------------------------
DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role` (
  `menu_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`menu_id`,`role_id`),
  KEY `FK_ROLE` (`role_id`),
  CONSTRAINT `FK_MENU_ROLE` FOREIGN KEY (`menu_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK_ROLE_MENU` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu_role
-- ----------------------------
INSERT INTO `menu_role` VALUES ('1', '1');
INSERT INTO `menu_role` VALUES ('1', '2');
INSERT INTO `menu_role` VALUES ('1', '3');

-- ----------------------------
-- Table structure for `privilege`
-- ----------------------------
DROP TABLE IF EXISTS `privilege`;
CREATE TABLE `privilege` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menuId` bigint(20) NOT NULL,
  `privilegeName` varchar(30) NOT NULL,
  `url` varchar(100) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(30) NOT NULL,
  `status` smallint(6) NOT NULL DEFAULT '1',
  `isDeleted` smallint(6) NOT NULL DEFAULT '0',
  `mark` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `privilegeName` (`privilegeName`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of privilege
-- ----------------------------
INSERT INTO `privilege` VALUES ('1', '1', '管理员登陆', 'admin', '2017-06-13 15:48:56', '管理员', '1', '0', '2');
INSERT INTO `privilege` VALUES ('2', '1', '欢迎页面', 'welcom', '2017-06-13 15:49:00', '管理员', '1', '0', '1');
INSERT INTO `privilege` VALUES ('3', '1', '登陆页面', 'db', '2017-06-13 16:44:16', '管理员', '1', '0', '3');
INSERT INTO `privilege` VALUES ('4', '1', '添加用户', 'newUser', '2017-06-13 15:49:05', '管理员', '1', '0', '1004');

-- ----------------------------
-- Table structure for `privilege_role`
-- ----------------------------
DROP TABLE IF EXISTS `privilege_role`;
CREATE TABLE `privilege_role` (
  `privilege_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`privilege_id`,`role_id`),
  KEY `FK_PRIVILEGE_ROLE` (`role_id`),
  CONSTRAINT `FK_PRIVILEGE` FOREIGN KEY (`privilege_id`) REFERENCES `privilege` (`id`),
  CONSTRAINT `FK_PRIVILEGE_ROLE` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of privilege_role
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `type` varchar(15) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(30) NOT NULL,
  `state` smallint(6) NOT NULL DEFAULT '1',
  `code` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员', 'ADMIN', '2017-06-11 10:25:31', '管理员', '1', '0001');
INSERT INTO `role` VALUES ('2', '普通用户', 'USER', '2017-06-11 10:27:31', '管理员', '1', '0002');
INSERT INTO `role` VALUES ('3', '数据库管理员', 'DBA', '2017-06-11 10:28:40', '管理员', '1', '0003');

-- ----------------------------
-- Table structure for `user_profile`
-- ----------------------------
DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE `user_profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_profile
-- ----------------------------
INSERT INTO `user_profile` VALUES ('2', 'ADMIN');
INSERT INTO `user_profile` VALUES ('3', 'DBA');
INSERT INTO `user_profile` VALUES ('1', 'USER');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_ROLE` (`role_id`),
  CONSTRAINT `FK_APP_USER_ROLE` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('3', '1');
