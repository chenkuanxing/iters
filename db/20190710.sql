/*工作日志模块表*/
DROP TABLE IF EXISTS `s_journal`;
CREATE TABLE `s_journal`  (
                            `id` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
                            `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志标题',
                            `publisher` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布人',
                            `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT ' 发布内容',
                            `perform_time` datetime(0) NULL DEFAULT NULL COMMENT '执行时间',
                            `perform_by` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行人',
                            `status` tinyint(4) NULL DEFAULT 1 COMMENT '可用状态',
                            `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间（发布时间）',
                            `created_user` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
                            `last_changed` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                            `last_changed_user` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工作日志模块表' ROW_FORMAT = Dynamic;

INSERT INTO `s_journal` VALUES ('1149193210757152769', '的VS犯得上广泛', '1', '但是广泛大使馆', '2019-07-11 13:55:24', '2', 1, '2019-07-11 13:46:27', '1', NULL, NULL);
INSERT INTO `s_journal` VALUES ('1149225047655206913', '的撒旦范德萨', '1', '的高三复读生', '2019-07-11 14:30:00', '2', 1, '2019-07-11 15:52:58', '1', NULL, NULL);

/*资源表*/
DROP TABLE IF EXISTS `s_resource`;
CREATE TABLE `s_resource`  (
                             `id` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
                             `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源名称',
                             `pid` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级编号',
                             `url` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接地址',
                             `sort` tinyint(4) NULL DEFAULT NULL COMMENT '序号',
                             `remark` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
                             `status` tinyint(4) NULL DEFAULT 1 COMMENT '可用状态',
                             `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                             `created_user` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
                             `last_changed` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                             `last_changed_user` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
                             `resource_tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源标记',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源表' ROW_FORMAT = Dynamic;

/*角色表*/
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role`  (
                         `id` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
                         `role_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
                         `role_tag` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色标签',
                         `status` tinyint(4) NULL DEFAULT 1 COMMENT '可用状态',
                         `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                         `created_user` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
                         `last_changed` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                         `last_changed_user` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
                         `data_type` tinyint(4) NULL DEFAULT 0 COMMENT '0:默认权限 1：全部 2：部分',
                         PRIMARY KEY (`id`) USING BTREE,
                         UNIQUE INDEX `sys_role_role_name_uindex`(`role_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

/*角色资源关联表*/
DROP TABLE IF EXISTS `s_role_resource`;
CREATE TABLE `s_role_resource`  (
                                  `id` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
                                  `role_id` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色编号',
                                  `resource_id` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源编号',
                                  `checked` tinyint(4) NULL DEFAULT NULL COMMENT '2部分选中，1全选，0未选中',
                                  `status` tinyint(4) NULL DEFAULT 1 COMMENT '可用状态',
                                  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                                  `created_user` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
                                  `last_changed` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                                  `last_changed_user` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色资源关联表' ROW_FORMAT = Dynamic;

/*用户信息*/
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user`  (
                         `id` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
                         `user_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户代码',
                         `password` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
                         `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
                         `sex` tinyint(4) NULL DEFAULT NULL COMMENT '性别',
                         `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件',
                         `mobile_no` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
                         `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                         `created_user` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
                         `last_changed` datetime(0) NULL DEFAULT NULL COMMENT '最后更新时间',
                         `last_changed_user` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后更新人',
                         `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态： 1:正常 -1:删除',
                         `role_id` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色ID',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息' ROW_FORMAT = Dynamic;

INSERT INTO `s_user` VALUES ('1', 'admin', '$2a$10$Sag0nPH949uilONl56kVNOZ5/fRJ2Yw3lxlzVgL/LTgaHiqOvKCjK', '管理员', 0, 'xinghui@qq.com', NULL, '2019-07-03 16:31:08', '-1', NULL, NULL, 1, NULL);
INSERT INTO `s_user` VALUES ('2', 'liuzhihui', '$2a$10$W10c/NssWlb8ew7n0c40hubYQpnw94j55djvQb7CSEGQ8gjpl3sNq', '刘志辉', 23, 'liuzhihui@qq.com', NULL, '2019-07-03 16:31:14', '-1', NULL, NULL, 1, NULL);

SET FOREIGN_KEY_CHECKS = 1;
/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : iters

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 18/07/2019 09:55:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for s_department
-- ----------------------------
DROP TABLE IF EXISTS `s_department`;
CREATE TABLE `s_department`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名称',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级组织',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '可用状态',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `created_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `last_changed` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `last_changed_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组织架构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for s_journal
-- ----------------------------
DROP TABLE IF EXISTS `s_journal`;
CREATE TABLE `s_journal`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志标题',
  `publisher` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布人',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT ' 发布内容',
  `perform_time` datetime(0) NULL DEFAULT NULL COMMENT '执行时间',
  `perform_by` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行人',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '可用状态',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间（发布时间）',
  `created_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `last_changed` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `last_changed_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工作日志模块表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_journal
-- ----------------------------
INSERT INTO `s_journal` VALUES ('1', '的VS犯得上广泛', '1', '但是广泛大使馆', '2019-07-11 13:55:24', '2', -1, '2019-07-11 13:46:27', '1', NULL, NULL);
INSERT INTO `s_journal` VALUES ('1149225047655206913', '的撒旦范德萨', '1', '的高三复读生', '2019-07-11 14:30:00', '2', 1, '2019-07-11 15:52:58', '1', NULL, NULL);
INSERT INTO `s_journal` VALUES ('1149228610682966017', 'fdgsfdsg', '1', '<p>dfds</p>', '2019-07-11 14:30:00', '2', 1, '2019-07-11 16:07:07', '1', NULL, NULL);

-- ----------------------------
-- Table structure for s_publish
-- ----------------------------
DROP TABLE IF EXISTS `s_publish`;
CREATE TABLE `s_publish`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `dep_id` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门编号',
  `publish_time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '可用状态',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `created_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `last_changed` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `last_changed_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `publish_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1149225047655206935 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公告发布表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_publish
-- ----------------------------
INSERT INTO `s_publish` VALUES (1149225047655206934, '世世代代', '1', '2019-07-16 13:07:55', '发大水范德萨发', 1, '2019-07-16 13:08:00', '1', NULL, NULL, '1');

-- ----------------------------
-- Table structure for s_resource
-- ----------------------------
DROP TABLE IF EXISTS `s_resource`;
CREATE TABLE `s_resource`  (
  `id` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源名称',
  `pid` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级编号',
  `url` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接地址',
  `sort` tinyint(4) NULL DEFAULT NULL COMMENT '序号',
  `remark` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '可用状态',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `created_user` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `last_changed` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `last_changed_user` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `resource_tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role`  (
  `id` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `role_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  `role_tag` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色标签',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '可用状态',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `created_user` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `last_changed` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `last_changed_user` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `data_type` tinyint(4) NULL DEFAULT 0 COMMENT '0:默认权限 1：全部 2：部分',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sys_role_role_name_uindex`(`role_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for s_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `s_role_resource`;
CREATE TABLE `s_role_resource`  (
  `id` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `role_id` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色编号',
  `resource_id` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源编号',
  `checked` tinyint(4) NULL DEFAULT NULL COMMENT '2部分选中，1全选，0未选中',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '可用状态',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `created_user` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `last_changed` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `last_changed_user` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色资源关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `user_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户代码',
  `password` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `sex` tinyint(4) NULL DEFAULT NULL COMMENT '性别',
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件',
  `dep_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门编号',
  `mobile_no` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `created_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `last_changed` datetime(0) NULL DEFAULT NULL COMMENT '最后更新时间',
  `last_changed_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后更新人',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态： 1:正常 -1:删除',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('1', 'admin', '$2a$10$Sag0nPH949uilONl56kVNOZ5/fRJ2Yw3lxlzVgL/LTgaHiqOvKCjK', '管理员', 0, 'xinghui@qq.com', NULL, NULL, '2019-07-03 16:31:08', '-1', NULL, NULL, 1, NULL);
INSERT INTO `s_user` VALUES ('2', 'liuzhihui', '$2a$10$W10c/NssWlb8ew7n0c40hubYQpnw94j55djvQb7CSEGQ8gjpl3sNq', '刘志辉', 23, 'liuzhihui@qq.com', NULL, NULL, '2019-07-03 16:31:14', '-1', NULL, NULL, 1, NULL);

SET FOREIGN_KEY_CHECKS = 1;
