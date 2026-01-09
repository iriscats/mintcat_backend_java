/*
 Navicat Premium Data Transfer

 Source Server         : pm_prd_44
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : 192.168.164.44:3306
 Source Schema         : ums

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 26/12/2025 17:18:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auth_token
-- ----------------------------
DROP TABLE IF EXISTS `auth_token`;
CREATE TABLE `auth_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'tokenId',
  `enableRefresh` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否支持 refreshToken, 默认: 1. 1 表示支持, 0 表示不支持',
  `providerId` varchar(20) DEFAULT NULL COMMENT '第三方服务商,如: qq,github',
  `accessToken` varchar(512) DEFAULT NULL COMMENT 'accessToken',
  `expireIn` bigint(20) DEFAULT '-1' COMMENT 'accessToken 过期时间, 无过期时间默认为 -1',
  `refreshTokenExpireIn` bigint(20) DEFAULT '-1' COMMENT 'refreshToken 过期时间, 无过期时间默认为 -1',
  `refreshToken` varchar(512) DEFAULT NULL COMMENT 'refreshToken',
  `uid` varchar(20) DEFAULT NULL COMMENT 'alipay userId',
  `openId` varchar(256) DEFAULT NULL COMMENT 'qq/mi/toutiao/wechatMp/wechatOpen/weibo/jd/kujiale/dingTalk/douyin/feishu',
  `accessCode` varchar(256) DEFAULT NULL COMMENT 'dingTalk, taobao 附带属性',
  `unionId` varchar(256) DEFAULT NULL COMMENT 'QQ附带属性',
  `scope` varchar(256) DEFAULT NULL COMMENT 'Google附带属性',
  `tokenType` varchar(20) DEFAULT NULL COMMENT 'Google附带属性',
  `idToken` varchar(256) DEFAULT NULL COMMENT 'Google附带属性',
  `macAlgorithm` varchar(20) DEFAULT NULL COMMENT '小米附带属性',
  `macKey` varchar(256) DEFAULT NULL COMMENT '小米附带属性',
  `code` varchar(256) DEFAULT NULL COMMENT '企业微信附带属性',
  `oauthToken` varchar(256) DEFAULT NULL COMMENT 'Twitter附带属性',
  `oauthTokenSecret` varchar(256) DEFAULT NULL COMMENT 'Twitter附带属性',
  `userId` varchar(64) DEFAULT NULL COMMENT 'Twitter附带属性',
  `screenName` varchar(64) DEFAULT NULL COMMENT 'Twitter附带属性',
  `oauthCallbackConfirmed` varchar(64) DEFAULT NULL COMMENT 'Twitter附带属性',
  `expireTime` bigint(20) DEFAULT '-1' COMMENT '过期时间, 基于 1970-01-01T00:00:00Z, 无过期时间默认为 -1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of auth_token
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_b
-- ----------------------------
DROP TABLE IF EXISTS `user_b`;
CREATE TABLE `user_b` (
  `id` int(40) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_name` varchar(100) NOT NULL COMMENT '用户名',
  `name` varchar(255) DEFAULT NULL COMMENT '中文名',
  `pwd` varchar(100) NOT NULL COMMENT '密码',
  `tel` varchar(100) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `user_group` varchar(100) DEFAULT NULL COMMENT '权限分组【分组ID】',
  `user_role` varchar(100) DEFAULT NULL COMMENT '岗位,dev',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `vali_flag` varchar(3) DEFAULT NULL COMMENT '有效标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_b
-- ----------------------------
BEGIN;
INSERT INTO `user_b` (`id`, `user_name`, `name`, `pwd`, `tel`, `email`, `user_group`, `user_role`, `update_time`, `create_time`, `vali_flag`) VALUES (4, 'hhm', NULL, '{bcrypt}$2a$10$cB0huIwhT8Q0cxRgJplU.O0kjNDkMQ7Scf/ViH8sBSEaDCQaXrYDy', NULL, NULL, NULL, NULL, '2025-09-23 09:21:57', '2025-09-23 09:21:57', '0');
COMMIT;

-- ----------------------------
-- Table structure for user_connection
-- ----------------------------
DROP TABLE IF EXISTS `user_connection`;
CREATE TABLE `user_connection` (
  `userId` varchar(36) NOT NULL COMMENT '本地用户id',
  `providerId` varchar(20) NOT NULL COMMENT '第三方服务商',
  `providerUserId` varchar(36) NOT NULL COMMENT '第三方用户id',
  `rank` int(11) NOT NULL COMMENT 'userId 绑定同一个 providerId 的排序',
  `displayName` varchar(64) DEFAULT NULL COMMENT '第三方用户名',
  `profileUrl` varchar(256) DEFAULT NULL COMMENT '主页',
  `imageUrl` varchar(256) DEFAULT NULL COMMENT '头像',
  `accessToken` varchar(512) NOT NULL,
  `tokenId` bigint(20) DEFAULT NULL COMMENT 'auth_token.id',
  `refreshToken` varchar(512) DEFAULT NULL,
  `expireTime` bigint(20) DEFAULT '-1' COMMENT '过期时间, 基于 1970-01-01T00:00:00Z, 无过期时间默认为 -1',
  PRIMARY KEY (`userId`,`providerId`,`providerUserId`),
  UNIQUE KEY `idx_userId_providerId_rank` (`userId`,`providerId`,`rank`),
  KEY `idx_providerId_providerUserId_rank` (`providerId`,`providerUserId`,`rank`),
  KEY `idx_tokenId` (`tokenId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_connection
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
