/*
 Navicat Premium Data Transfer

 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Schema         : generator

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 31/05/2022 15:22:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dict_data
-- ----------------------------
DROP TABLE IF EXISTS `dict_data`;
CREATE TABLE `dict_data`
(
    `id`          int(6) UNSIGNED                                         NOT NULL AUTO_INCREMENT COMMENT '主键',
    `type_id`     int(6)                                                  NOT NULL COMMENT '字典类型id',
    `name`        text CHARACTER SET utf8 COLLATE utf8_general_ci         NOT NULL COMMENT '字典名称',
    `value`       varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '编码',
    `sort`        int(6) UNSIGNED                                         NOT NULL COMMENT '排序',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '备注',
    `status`      tinyint(1) UNSIGNED                                     NOT NULL DEFAULT 1 COMMENT '状态（0正常 1停用）',
    `create_date` datetime                                                NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_date` datetime                                                NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 29
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '系统字典值表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for dict_type
-- ----------------------------
DROP TABLE IF EXISTS `dict_type`;
CREATE TABLE `dict_type`
(
    `id`          int(6) UNSIGNED                                         NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
    `code`        varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '编码',
    `sort`        int(6) UNSIGNED                                         NOT NULL COMMENT '排序',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '备注',
    `status`      tinyint(1) UNSIGNED                                     NOT NULL DEFAULT 1 COMMENT '状态（0停用 1正常）',
    `create_date` datetime                                                NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_date` datetime                                                NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `code_index` (`code`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '系统字典类型表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for gen_database
-- ----------------------------
DROP TABLE IF EXISTS `gen_database`;
CREATE TABLE `gen_database`
(
    `id`                int(10) UNSIGNED                                              NOT NULL AUTO_INCREMENT COMMENT '主键',
    `address`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据库地址',
    `port`              varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NOT NULL COMMENT '数据库端口',
    `name`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '库名称',
    `type`              tinyint(1) UNSIGNED                                           NOT NULL DEFAULT 0 COMMENT '数据库类型',
    `driver_class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据库驱动',
    `user_name`         text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NOT NULL COMMENT '用户名',
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
    `state`       tinyint(1) UNSIGNED                                   NOT NULL DEFAULT 1 COMMENT '盐是否持久化(0:否 1:是)',
    `create_date` datetime                                              NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_date` datetime                                              NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
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
-- Initialization data
-- ----------------------------

-- ----------------------------
-- Records of dict_type
-- ----------------------------
INSERT INTO `dict_type` (`id`, `name`, `code`, `sort`, `remark`, `status`, `create_date`)
VALUES (1, '通用状态', 'common_status', 1, '', 1, NOW());
INSERT INTO `dict_type` (`id`, `name`, `code`, `sort`, `remark`, `status`, `create_date`)
VALUES (2, '是否标识', 'status', 2, NULL, 1, NOW());
INSERT INTO `dict_type` (`id`, `name`, `code`, `sort`, `remark`, `status`, `create_date`)
VALUES (3, '数据库类型', 'database_type', 3, NULL, 1, NOW());
INSERT INTO `dict_type` (`id`, `name`, `code`, `sort`, `remark`, `status`, `create_date`)
VALUES (4, 'java数据类型', 'java_data_type', 4, NULL, 1, NOW());
INSERT INTO `dict_type` (`id`, `name`, `code`, `sort`, `remark`, `status`, `create_date`)
VALUES (5, '网页标签', 'web_label', 5, NULL, 1, NOW());
INSERT INTO `dict_type` (id, `name`, `code`, `sort`, `remark`, `status`, `create_date`)
VALUES (6, '查询条件', 'query_type', 6, NULL, 1, NOW());

-- ----------------------------
-- Records of dict_data
-- ----------------------------
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (1, 1, '停用', '0', 1, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (2, 1, '正常', '1', 2, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (3, 2, '否', '0', 1, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (4, 2, '是', '1', 2, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (5, 3, 'MySQL', '0', 1, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (6, 3, 'PostgreSQL', '1', 2, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (7, 3, 'Oracle', '2', 3, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (8, 3, 'SQLite', '3', 4, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (9, 3, 'MariaDB', '4', 5, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (10, 4, 'String', 'String', 1, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (11, 4, 'Integer', 'Integer', 2, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (12, 4, 'Float', 'Float', 3, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (13, 4, 'Double', 'Double', 4, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (14, 4, 'Long', 'Long', 5, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (15, 4, 'Boolean', 'Boolean', 6, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (16, 4, 'LocalDateTime', 'LocalDateTime', 7, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (17, 4, 'BigDecimal', 'BigDecimal', 8, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (18, 5, '输入框', 'input', 1, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (19, 5, '文本域', 'text', 2, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (20, 5, '下拉框', 'dropdown', 3, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (21, 5, '复选框', 'checkbox', 4, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (22, 5, '单选框', 'radio', 5, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (23, 5, '日期控件', 'datetime', 6, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (24, 6, '等于', 'eq', 1, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (25, 6, '不等于', 'neq', 2, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (26, 6, '大于', 'gt', 3, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (27, 6, '小于', 'lt', 4, NULL, 1, NOW());
INSERT INTO `dict_data` (`id`, `type_id`, `name`, `value`, `sort`, `remark`, `status`, `create_date`)
VALUES (28, 6, '范围', 'like', 5, NULL, 1, NOW());

-- ----------------------------
-- Records of gen_system_config
-- ----------------------------
INSERT INTO `gen_system_config` (`id`, `private_key`, `public_key`, `salt`, `state`, `create_date`)
VALUES (1,
        'MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAI/Ce+SRYmPbM5bzEvR+f5tAsmizhziUy4YG8FnelYNhAxz2FJagnB4Rk+cQjBAg7pjsMg87ofS9kfJA16/iZbRfepYIo0YsyfyEzcj3P3QW5M1ryGgHxAmbY8/+p3d283hxaNVlBJLyZgn9Dewjui2/Ims5AtNLZ2K9g5LJy7XrAgMBAAECgYEAjPRCCdBKymHo6X6X/UXy1ETRKTutv9qU22r+Sv+lVVXPeZwGf9ImgIz3mb1tt3qytxoP00PC/nbJHOBbGsc+s+0YgGT5K3CPQUBJ8eulFQpZS8YG6a5MCN4R1pbLHp0VEyx3TJd7rtTWWLxRmpd6UyoFjrJdddagg46uy30lR0ECQQDOjMf5F8Orw3qZJ/ADAco7gXnlXh00/id5lCLKWZeziDK58RiFQ0Sqo4NBLfFV3pt7q7/VnOZH6jTXHI1Lr+gRAkEAsi1b1+AICe1ABR3Li7bYIPRHBrIBHZm4f96GrVZi2aBy7aGpGkUmJuldCxUO3vP/kMXkKOOum92EqFC3+zeaOwJAO1BqTLnqUvT7y+eQIQzuilAQNTHlqUYjB+Lmar13tvKxvnvh0sMSgP4bvyf2WfZnKAiWOj2oU5aKPgvQ9584QQJBAK9FV1dgFghNzfA6gwQI9UpVgZjgeaB4mrmOWMUieVLDNsrzf7lL1F/qp5BDQsGnG/t57xsZ/aVtnxVDcfg5lgkCQBUJgZauo5SSeB8h3tpHE+j+4EIdvi1LcD3AB2BYfVgohB7DA3iOA2Z4el84pbiVsidI9uFMa0rbWFN6Duy76pQ=',
        'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCPwnvkkWJj2zOW8xL0fn+bQLJos4c4lMuGBvBZ3pWDYQMc9hSWoJweEZPnEIwQIO6Y7DIPO6H0vZHyQNev4mW0X3qWCKNGLMn8hM3I9z90FuTNa8hoB8QJm2PP/qd3dvN4cWjVZQSS8mYJ/Q3sI7otvyJrOQLTS2divYOSycu16wIDAQAB',
        'S3xrre5rM0SBHUDRGpX7Zj5PsG0ynJ2A6LfYS/NzVD5cJqz+NHmtsxSE4YAmYo8O332XksC+Qm6WcjtCzHKLA/Z1dpNXbqGR2Kvyh4V/QPhyjOV+nluwP7QQqEcddz59XGySjJYuMqZNYKoSa719j3vhH1cf4r1goNLWRfA2FK4=',
        1, NOW());

-- ----------------------------
-- Records of gen_tables_config
-- ----------------------------
INSERT INTO `gen_tables_config` (`id`, `name`, `author`, `package_name`, `module_name`, `table_prefix`, `remove_prefix`,
                                 `create_date`)
VALUES (1, '默认', 'Joshua', 'com.moonlit', 'generator', 'gen_', 1, NOW());
