/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : 127.0.0.1:3306
 Source Schema         : scaffold

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 23/07/2019 14:17:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log`  (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `login_time` datetime(0) NOT NULL COMMENT '登陆时间',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器',
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `username`(`username`) USING BTREE,
  INDEX `login_time`(`login_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `permission_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `parent_id` bigint(20) NOT NULL COMMENT '父ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'url',
  `perms` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限值',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型 0菜单 1按钮',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '排序值',
  `create_time` datetime(0) NOT NULL COMMENT '添加时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`permission_id`) USING BTREE,
  INDEX `parent_id`(`parent_id`) USING BTREE,
  INDEX `type`(`type`) USING BTREE,
  INDEX `order`(`order_num`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, 0, '系统管理', NULL, NULL, '0', 'layui-icon-setting', 1, '2019-07-09 15:46:36', NULL);
INSERT INTO `permission` VALUES (2, 1, '菜单管理', '/system/menu', 'menu:view', '0', 'layui-icon-EURO', 1, '2019-07-09 15:52:26', '2019-07-15 11:11:25');
INSERT INTO `permission` VALUES (3, 2, '新增菜单', NULL, 'menu:add', '1', NULL, 1, '2019-07-12 15:53:39', '2019-07-15 11:13:26');
INSERT INTO `permission` VALUES (13, 2, '删除菜单', NULL, 'menu:delete', '1', NULL, 2, '2019-07-12 18:39:12', NULL);
INSERT INTO `permission` VALUES (14, 2, '修改菜单', NULL, 'menu:update', '1', NULL, 3, '2019-07-15 11:06:34', NULL);
INSERT INTO `permission` VALUES (15, 1, '用户管理', '/system/user', 'user:view', '0', 'layui-icon-team', 2, '2019-07-15 15:48:10', NULL);
INSERT INTO `permission` VALUES (16, 15, '修改用户', NULL, 'user:update', '1', NULL, 1, '2019-07-15 18:09:03', NULL);
INSERT INTO `permission` VALUES (17, 15, '删除用户', NULL, 'user:delete', '1', NULL, 2, '2019-07-15 18:15:43', NULL);
INSERT INTO `permission` VALUES (18, 15, '新增用户', NULL, 'user:add', '1', NULL, 3, '2019-07-15 18:23:37', NULL);
INSERT INTO `permission` VALUES (19, 15, '重置密码', NULL, 'user:password:reset', '1', NULL, 4, '2019-07-15 18:24:08', NULL);
INSERT INTO `permission` VALUES (20, 1, '角色管理', '/system/role', 'role:view', '0', 'layui-icon-team', 3, '2019-07-18 16:36:15', '2019-07-18 16:36:40');
INSERT INTO `permission` VALUES (21, 20, '添加角色', NULL, 'role:add', '1', NULL, 1, '2019-07-18 17:15:16', '2019-07-19 10:51:27');
INSERT INTO `permission` VALUES (22, 20, '修改角色', NULL, 'role:update', '1', NULL, 2, '2019-07-18 17:15:40', NULL);
INSERT INTO `permission` VALUES (23, 20, '删除角色', NULL, 'role:delete', '1', NULL, 3, '2019-07-18 17:15:59', NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime(0) NOT NULL COMMENT '添加时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '系统管理员', '系统管理员，拥有所有操作权限 ^_^', '2019-07-09 15:45:22', '2019-07-19 10:49:58');
INSERT INTO `role` VALUES (2, '客户管理员', '对客户信息进行管理', '2019-07-18 18:21:47', '2019-07-19 10:32:22');
INSERT INTO `role` VALUES (3, '仓库管理员', '此角色用于一切测试', '2019-07-18 18:28:00', '2019-07-19 09:59:11');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `role_permission_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色权限ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`role_permission_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (34, 3, 1);
INSERT INTO `role_permission` VALUES (35, 3, 2);
INSERT INTO `role_permission` VALUES (36, 3, 3);
INSERT INTO `role_permission` VALUES (37, 3, 13);
INSERT INTO `role_permission` VALUES (44, 2, 1);
INSERT INTO `role_permission` VALUES (45, 2, 15);
INSERT INTO `role_permission` VALUES (46, 2, 16);
INSERT INTO `role_permission` VALUES (47, 2, 17);
INSERT INTO `role_permission` VALUES (48, 2, 18);
INSERT INTO `role_permission` VALUES (62, 1, 1);
INSERT INTO `role_permission` VALUES (63, 1, 2);
INSERT INTO `role_permission` VALUES (64, 1, 3);
INSERT INTO `role_permission` VALUES (65, 1, 13);
INSERT INTO `role_permission` VALUES (66, 1, 14);
INSERT INTO `role_permission` VALUES (67, 1, 15);
INSERT INTO `role_permission` VALUES (68, 1, 16);
INSERT INTO `role_permission` VALUES (69, 1, 17);
INSERT INTO `role_permission` VALUES (70, 1, 18);
INSERT INTO `role_permission` VALUES (71, 1, 19);
INSERT INTO `role_permission` VALUES (72, 1, 20);
INSERT INTO `role_permission` VALUES (73, 1, 21);
INSERT INTO `role_permission` VALUES (74, 1, 22);
INSERT INTO `role_permission` VALUES (75, 1, 23);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作用户',
  `operation` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作内容',
  `time` bigint(20) NULL DEFAULT NULL COMMENT '耗时',
  `method` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法',
  `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '方法参数',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者IP',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 107 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态 0锁定 1有效',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别 0男 1女 2保密',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注描述',
  `theme` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主题',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最近登录时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '856aea89ad509f163284abb75579dcfc', 'AnthonyZero', '738562125@qq.com', '15888886666', '1', '0', '20180414165936.jpg', '我是一个粉刷匠！！！ 啦啦啦', 'black', '2019-07-09 13:28:58', '2019-07-23 14:12:35', '2019-07-23 14:10:24');
INSERT INTO `user` VALUES (2, 'test', 'c8ca90a2ea885784ed3517fd502329e8', '小红', '736284521@qq.com', '13555552368', '1', '1', 'default.jpg', '', 'black', '2019-07-18 14:56:19', '2019-07-23 14:12:48', '2019-07-19 10:32:57');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户角色ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_role_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (39, 1, 1);
INSERT INTO `user_role` VALUES (40, 2, 2);

-- ----------------------------
-- Function structure for findPermsChildren
-- ----------------------------
DROP FUNCTION IF EXISTS `findPermsChildren`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `findPermsChildren`(rootId INT) RETURNS varchar(4000) CHARSET utf8
BEGIN
    DECLARE sTemp VARCHAR(4000);
    DECLARE sTempChd VARCHAR(4000);
    SET sTemp = '$';
    SET sTempChd = CAST(rootId as CHAR);
    WHILE sTempChd is not null DO
        SET sTemp = CONCAT(sTemp,',',sTempChd);
    SELECT GROUP_CONCAT(permission_id) INTO sTempChd FROM permission
    WHERE FIND_IN_SET(parent_id,sTempChd)>0;
END WHILE;
RETURN sTemp;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
