DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户代码',
  `password` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `sex` tinyint(4) NULL DEFAULT NULL COMMENT '性别',
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件',
  `mobile_no` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `created_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `last_changed` datetime(0) NULL DEFAULT NULL COMMENT '最后更新时间',
  `last_changed_user` bigint(20) NULL DEFAULT NULL COMMENT '最后更新人',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态： 1:正常 -1:删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES (1, 'admin', '$2a$10$Sag0nPH949uilONl56kVNOZ5/fRJ2Yw3lxlzVgL/LTgaHiqOvKCjK', '管理员', 0, 'xinghui@qq.com', NULL, '2019-07-03 16:31:08', -1, NULL, NULL, 1);
INSERT INTO `s_user` VALUES (2, 'liuzhihui', '$2a$10$W10c/NssWlb8ew7n0c40hubYQpnw94j55djvQb7CSEGQ8gjpl3sNq', '刘志辉', 23, 'liuzhihui@qq.com', NULL, '2019-07-03 16:31:14', -1, NULL, NULL, 1);

SET FOREIGN_KEY_CHECKS = 1;