/*
 Navicat Premium Data Transfer

 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Schema         : generator

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 26/05/2022 22:12:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dict_data
-- ----------------------------
DROP TABLE IF EXISTS `dict_data`;
CREATE TABLE `dict_data`  (
                              `id` int(6) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
                              `type_id` int(6) NOT NULL COMMENT '字典类型id',
                              `name` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典名称',
                              `value` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编码',
                              `sort` int(6) UNSIGNED NOT NULL COMMENT '排序',
                              `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
                              `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态（0正常 1停用）',
                              `create_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                              `update_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统字典值表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for dict_type
-- ----------------------------
DROP TABLE IF EXISTS `dict_type`;
CREATE TABLE `dict_type`  (
                              `id` int(6) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
                              `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
                              `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编码',
                              `sort` int(6) UNSIGNED NOT NULL COMMENT '排序',
                              `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
                              `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态（0停用 1正常）',
                              `create_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                              `update_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              PRIMARY KEY (`id`) USING BTREE,
                              UNIQUE INDEX `code_index`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统字典类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for gen_database
-- ----------------------------
DROP TABLE IF EXISTS `gen_database`;
CREATE TABLE `gen_database`  (
                                 `id`                int(10) UNSIGNED                                              NOT NULL AUTO_INCREMENT COMMENT '主键',
                                 `address`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据库地址',
                                 `port`              varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NOT NULL COMMENT '数据库端口',
                                 `name`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '库名称',
                                 `type`              tinyint(1) UNSIGNED                                           NOT NULL DEFAULT 0 COMMENT '数据库类型',
                                 `driver_class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据库驱动',
                                 `user_name`         varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户名',
                                 `password`          text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NOT NULL COMMENT '密码（私钥加密）',
                                 `create_date`       datetime                                                      NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_date`       datetime                                                      NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '数据库详情表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for gen_system_config
-- ----------------------------
DROP TABLE IF EXISTS `gen_system_config`;
CREATE TABLE `gen_system_config`
(
    `id`          int(10) UNSIGNED                                      NOT NULL AUTO_INCREMENT COMMENT 'id',
    `private_key` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '私钥',
    `public_key`  text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '公钥',
    `salt`        text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '盐',
    `create_date` datetime                                              NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_date` datetime                                              NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '系统配置表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for gen_tables
-- ----------------------------
DROP TABLE IF EXISTS `gen_tables`;
CREATE TABLE `gen_tables`
(
    `id`            int(10) UNSIGNED                                              NOT NULL AUTO_INCREMENT COMMENT '主键',
    `database_id`   int(10) UNSIGNED                                              NOT NULL COMMENT '数据库id',
    `table_name`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表名称',
    `table_comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表描述',
    `class_name`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类名',
    `business_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务名',
    `function_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '功能名',
    `config_id`     int(10) UNSIGNED                                              NOT NULL COMMENT '配置表id',
    `create_date`   datetime                                                      NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_date`   datetime                                                      NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '表详情表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for gen_tables_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_tables_column`;
CREATE TABLE `gen_tables_column`
(
    `id`             int(10) UNSIGNED                                              NOT NULL AUTO_INCREMENT COMMENT '主键',
    `table_id`       int(10) UNSIGNED                                              NOT NULL COMMENT '表id',
    `column_name`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段名',
    `column_comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '字段描述',
    `sort`           int(6)                                                        NULL     DEFAULT NULL COMMENT '排序',
    `column_type`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '列类型',
    `is_primary_key` tinyint(1) UNSIGNED                                           NOT NULL DEFAULT 1 COMMENT '是否主键（0否，1是）',
    `is_increment`   tinyint(1) UNSIGNED                                           NOT NULL DEFAULT 1 COMMENT '是否自增（0否，1是）',
    `is_required`    tinyint(1) UNSIGNED                                           NOT NULL DEFAULT 1 COMMENT '是否为空（0否，1是）',
    `java_type`      varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT 'String' COMMENT 'JAVA类型',
    `java_field`     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT 'JAVA字段名',
    `is_insert`      tinyint(1)                                                    NOT NULL DEFAULT 1 COMMENT '是否为插入字段（0否，1是）',
    `is_edit`        tinyint(1)                                                    NOT NULL DEFAULT 1 COMMENT '是否编辑字段（0否，1是）',
    `is_list`        tinyint(1)                                                    NOT NULL DEFAULT 1 COMMENT '是否列表字段（0否，1是）',
    `is_query`       tinyint(1)                                                    NOT NULL DEFAULT 1 COMMENT '是否查询字段（0否，1是）',
    `query_type`     varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NOT NULL DEFAULT 'eq' COMMENT '查询方式（等于-eq、不等于-neq、大于-gt、小于-lt、范围-lk）',
    `html_type`      varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT 'input' COMMENT '显示类型（文本框-input、文本域-text、下拉框-dropdown、复选框-checkbox、单选框-radio、日期控件-datetime）',
    `dict_type`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT '' COMMENT '字典类型',
    `create_date`    datetime                                                      NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_date`    datetime                                                      NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '字段详情表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for gen_tables_config
-- ----------------------------
DROP TABLE IF EXISTS `gen_tables_config`;
CREATE TABLE `gen_tables_config`
(
    `id`            int(10) UNSIGNED                                              NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置名稱',
    `author`        varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '作者',
    `package_name`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '包名',
    `module_name`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '模块名',
    `table_prefix`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '表前綴',
    `remove_prefix` tinyint(1) UNSIGNED                                           NOT NULL DEFAULT 0 COMMENT '移除表前綴(0 否 1 是)',
    `create_date`   datetime                                                      NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '配置表'
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Records of dict_type
-- ----------------------------
INSERT INTO `dict_type`
VALUES (1, '通用状态', 'common_status', 1, '', 1, '2021-11-18 16:22:21', '2021-11-18 11:28:10');
INSERT INTO `dict_type`
VALUES (2, '是否标识', 'status', 2, NULL, 1, '2021-11-22 19:14:16', '2021-11-22 19:39:06');
INSERT INTO `dict_type`
VALUES (3, '数据库类型', 'database_type', 3, NULL, 1, '2021-11-22 19:38:56', NULL);
INSERT INTO `dict_type`
VALUES (4, 'java数据类型', 'java_data_type', 4, NULL, 1, '2022-05-25 11:34:52', '2022-05-25 17:05:09');
INSERT INTO `dict_type`
VALUES (5, '网页标签', 'web_label', 5, NULL, 1, '2022-05-25 11:43:03', '2022-05-25 17:05:02');
INSERT INTO `dict_type`
VALUES (6, '查询条件', 'query_type', 6, NULL, 1, '2022-05-25 11:47:23', '2022-05-25 17:04:55');

-- ----------------------------
-- Records of dict_data
-- ----------------------------
INSERT INTO `dict_data`
VALUES (1, 1, '停用', '0', 1, NULL, 1, '2021-11-18 10:01:39', NULL);
INSERT INTO `dict_data`
VALUES (2, 1, '正常', '1', 2, NULL, 1, '2021-11-18 10:01:52', NULL);
INSERT INTO `dict_data`
VALUES (3, 2, '否', '0', 1, NULL, 1, '2021-11-22 19:36:00', NULL);
INSERT INTO `dict_data`
VALUES (4, 2, '是', '1', 2, NULL, 1, '2021-11-22 19:36:07', NULL);
INSERT INTO `dict_data`
VALUES (5, 3, 'MySQL', '0', 1, NULL, 1, '2021-11-22 20:03:32', '2021-11-22 20:20:46');
INSERT INTO `dict_data`
VALUES (6, 3, 'PostgreSQL', '1', 2, NULL, 1, '2021-11-22 20:20:39', NULL);
INSERT INTO `dict_data`
VALUES (7, 3, 'Oracle', '2', 3, NULL, 1, '2021-11-22 20:20:57', NULL);
INSERT INTO `dict_data`
VALUES (8, 3, 'SQLite', '3', 4, NULL, 1, '2021-11-22 20:21:10', NULL);
INSERT INTO `dict_data`
VALUES (9, 3, 'MariaDB', '4', 5, NULL, 1, '2021-11-22 20:21:43', '2022-05-15 16:18:56');
INSERT INTO `dict_data`
VALUES (10, 4, 'String', 'String', 1, NULL, 1, '2022-05-25 11:35:21', NULL);
INSERT INTO `dict_data`
VALUES (11, 4, 'Integer', 'Integer', 2, NULL, 1, '2022-05-25 11:35:39', NULL);
INSERT INTO `dict_data`
VALUES (12, 4, 'Float', 'Float', 3, NULL, 1, '2022-05-25 11:35:46', NULL);
INSERT INTO `dict_data`
VALUES (13, 4, 'Double', 'Double', 4, NULL, 1, '2022-05-25 11:35:54', NULL);
INSERT INTO `dict_data`
VALUES (14, 4, 'Long', 'Long', 5, NULL, 1, '2022-05-25 11:36:03', NULL);
INSERT INTO `dict_data`
VALUES (15, 4, 'Boolean', 'Boolean', 6, NULL, 1, '2022-05-25 11:36:13', NULL);
INSERT INTO `dict_data`
VALUES (16, 4, 'LocalDateTime', 'LocalDateTime', 7, NULL, 1, '2022-05-25 11:37:11', '2022-05-25 11:39:24');
INSERT INTO `dict_data`
VALUES (17, 4, 'BigDecimal', 'BigDecimal', 8, NULL, 1, '2022-05-25 11:37:29', NULL);
INSERT INTO `dict_data`
VALUES (18, 5, '输入框', 'input', 1, NULL, 1, '2022-05-25 11:43:44', '2022-05-25 17:04:39');
INSERT INTO `dict_data`
VALUES (19, 5, '文本域', 'text', 2, NULL, 1, '2022-05-25 11:44:11', NULL);
INSERT INTO `dict_data`
VALUES (20, 5, '下拉框', 'dropdown', 3, NULL, 1, '2022-05-25 11:44:22', NULL);
INSERT INTO `dict_data`
VALUES (21, 5, '复选框', 'checkbox', 4, NULL, 1, '2022-05-25 11:44:38', NULL);
INSERT INTO `dict_data`
VALUES (22, 5, '单选框', 'radio', 5, NULL, 1, '2022-05-25 11:44:50', NULL);
INSERT INTO `dict_data`
VALUES (23, 5, '日期控件', 'datetime', 6, NULL, 1, '2022-05-25 11:45:01', NULL);
INSERT INTO `dict_data`
VALUES (24, 6, '等于', 'eq', 1, NULL, 1, '2022-05-25 11:48:18', NULL);
INSERT INTO `dict_data`
VALUES (25, 6, '不等于', 'neq', 2, NULL, 1, '2022-05-25 11:48:30', NULL);
INSERT INTO `dict_data`
VALUES (26, 6, '大于', 'gt', 3, NULL, 1, '2022-05-25 11:48:41', NULL);
INSERT INTO `dict_data`
VALUES (27, 6, '小于', 'lt', 4, NULL, 1, '2022-05-25 11:48:52', NULL);
INSERT INTO `dict_data`
VALUES (28, 6, '范围', 'like', 5, NULL, 1, '2022-05-25 11:49:08', NULL);

-- ----------------------------
-- Records of gen_system_config
-- ----------------------------
INSERT INTO `gen_system_config`
VALUES (1,
        'MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAI3O2gb7swUJYsywTDH60t83geie6DLf69V+UPHyh0cNjTFRbt4HH6Ak3akgw9XLuqI5XaDZWFF84tibgXzZ1qEqUnXdzRa7nYOdORNZ+Q51ODgyR6/zXZ3Id+q2D+fS3lQo9U3e7HZFGnomcpCoTArpnsx2xIeqnjkTDkEuUfifAgMBAAECgYEAgEaUPOrkJnM2FziGxFNC+z5H2jgcjSC3QVlROXuM2T4t8WXbZyaEeiW/CSWlYLk4q1Q/GKhi1tukjBInENww5BrL9cCNqO1KlHNTk7vmYpEsSg8p2DFh0BK5r1cv+WsRraQ51AQ0YHNYxaSsgU3d77Px3o3ctfxi+3XG0jg7JckCQQDqHLfEdOFKILJE41gem5HB0+Zb7bbRWYQgsLz3zuZwZQBR5U9rncd9vi3/I6ieSc0oUI8ZP/rldJ+p9qY3RZvdAkEAmxDo4L0owaZVr/7i6iwsvcK6oHbUpHvfxV4s44K2wq0KP6CaZnjFI9OiBlVRsmlfPDN8YgrMAsRe/EDZYY6MqwJAOjZ523fUrUIEEe0V9EZqr++o0CMD2nqPyDEqS9Q+qKP0uGh0nyXUfQfVGCQdwX5IbUXaz0SBdpzRNsoF+qhsYQJANIvGXmG7LePvpXP58OCHSMZz92xNIm/XpEoFbBMfW1jH4EfMCm1iYIGWpg7DYHHUk9HelFqUR1vD1DclcqmbgQJBAL95lDR8LatbwHTQBiOv5u+qqW41jVdNhf16n/qrQlYUryT8nfF75lQVAl22EcZWZqiW1ShOQuomiLygolOykmI=',
        'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCNztoG+7MFCWLMsEwx+tLfN4Honugy3+vVflDx8odHDY0xUW7eBx+gJN2pIMPVy7qiOV2g2VhRfOLYm4F82dahKlJ13c0Wu52DnTkTWfkOdTg4Mkev812dyHfqtg/n0t5UKPVN3ux2RRp6JnKQqEwK6Z7MdsSHqp45Ew5BLlH4nwIDAQAB',
        'QqM2zXsO-WqkjcuVqPOPo2hMf-z6RE0q3i0XtKyxtKUoN6tzuq7wYWX8X5wqMmLi1hvFDEvoqaQqBkxslCf8g_J2grRglsW5q1ZMa9BZeYNDrfrgD7Jd3SRp96Kvo9F_WtUg8SDaGRabdNrkPocLjTH5n6lq1GXAHMhFkjWuEXU',
        '2021-10-14 08:33:45', '2022-05-10 15:22:11');

-- ----------------------------
-- Records of gen_tables_config
-- ----------------------------
INSERT INTO `gen_tables_config`
VALUES (1, '默认', 'Joshua', 'com.moonlit', 'generator', 'gen_', 1, '2022-02-11 00:04:59');
