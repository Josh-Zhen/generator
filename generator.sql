/*
 Navicat Premium Data Transfer

 Source Server         : 本地5.7.23
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : localhost:3307
 Source Schema         : generator

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 22/11/2021 23:52:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dict_data
-- ----------------------------
DROP TABLE IF EXISTS `dict_data`;
CREATE TABLE `dict_data`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_id` bigint(20) NOT NULL COMMENT '字典类型id',
  `name` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典名称',
  `value` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编码',
  `sort` int(11) NOT NULL COMMENT '排序',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` tinyint(4) NOT NULL COMMENT '状态（0正常 1停用）',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统字典值表';

-- ----------------------------
-- Records of dict_data
-- ----------------------------
BEGIN;
INSERT INTO `dict_data` VALUES (1, 1, '停用', '0', 1, NULL, 1, '2021-11-18 10:01:39', NULL), (2, 1, '正常', '1', 2, NULL, 1, '2021-11-18 10:01:52', NULL), (3, 2, '否', '0', 1, NULL, 1, '2021-11-22 19:36:00', NULL), (4, 2, '是', '1', 2, NULL, 1, '2021-11-22 19:36:07', NULL), (5, 3, 'MySQL', '0', 1, NULL, 1, '2021-11-22 20:03:32', '2021-11-22 20:20:46'), (6, 3, 'PostgreSQL', '1', 2, NULL, 1, '2021-11-22 20:20:39', NULL), (7, 3, 'Oracle', '2', 3, NULL, 1, '2021-11-22 20:20:57', NULL), (8, 3, 'SQLite', '3', 4, NULL, 1, '2021-11-22 20:21:10', NULL), (9, 3, 'SQL_Server', '4', 5, NULL, 1, '2021-11-22 20:21:27', NULL), (10, 3, 'MariaDB', '5', 6, NULL, 1, '2021-11-22 20:21:43', NULL), (11, 3, 'MongoDB', '6', 7, NULL, 1, '2021-11-22 20:22:01', NULL);
COMMIT;

-- ----------------------------
-- Table structure for dict_type
-- ----------------------------
DROP TABLE IF EXISTS `dict_type`;
CREATE TABLE `dict_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编码',
  `sort` int(11) NOT NULL COMMENT '排序',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` tinyint(4) NOT NULL COMMENT '状态（0停用 1正常）',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code_index`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统字典类型表';

-- ----------------------------
-- Records of dict_type
-- ----------------------------
BEGIN;
INSERT INTO `dict_type` VALUES (1, '通用状态', 'common_status', 1, '', 1, '2021-11-18 16:22:21', '2021-11-18 11:28:10'), (2, '是否标识', 'status', 2, NULL, 1, '2021-11-22 19:14:16', '2021-11-22 19:39:06'), (3, '数据库类型', 'database_type', 3, NULL, 1, '2021-11-22 19:38:56', NULL);
COMMIT;

-- ----------------------------
-- Table structure for gen_config
-- ----------------------------
DROP TABLE IF EXISTS `gen_config`;
CREATE TABLE `gen_config`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `author` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '作者',
  `private_key` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '私钥',
  `public_key` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '公钥',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '默认选择（0：否、1：是）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '作者相关配置';

-- ----------------------------
-- Records of gen_config
-- ----------------------------
BEGIN;
INSERT INTO `gen_config` VALUES (1, 'Joshua', 'MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAODhdyp8gFaqimgSoBnTZihI0ABhZkKA2j+1ysG+NB7INZwalQ6A96PVtKZPGLituqolHEmPEYwLoggsPcciUNE9mTHPYf3oDPEDtxDmYtazq8XxskFvT8h14O3GwHtVTExYIZwyEyczyWEvnHPTiVwYZzvKIBdLaSj4+x5yW3kDAgMBAAECgYAlBHg9QilOGtyVrRs1pRrX3sR+i4ntwJAslJw/sDOcLZDFlYqbzJb3HSKTjcmf/NkRUPKLGUXmK7QMbFvu/7MVr2/MrdtzhDp6FrytPEnK0k3CCBlaRCQJYElZ6pfN7Gj0Rgb2Tz/1u0Eaavl5PiydZDQudZAKK1e7SvwHIkN3YQJBAPTcT9wAffo54pPbkrDeSRfYs1iCfNtP1ETuw9PsdUCtodw6D5X/d2eEH1SWb4De7NOXberk7kefbpsFUivQHycCQQDrHHbvrQYPGBp2h/a8I0fvVzGX31B0hsyyWj03+/kSRf6DO0LN2y+US5PFGXhrNL5psspRjPzkOrcrktc7YIDFAkEAjcZKTv3R82I0uJu38cSi2bXVEfhrxqgQJeGBbWFJ+qsOPu83Owhx4HP1mAqKgTmSMmlJcWogNUQwzH1mOIwGnQJAOzUX3pO8CuEPCPqEcCySWPukSZK7OB0aP2/qKscRmkB2L6Yk1KJ2AOpndCNN5/GIYiXcPV078l228wovmYxb4QJAH/ajs+ADYG4vaOtL9qQfNYhmVf1UCSJrXX7+jBS8SbAot78uyC7w0N8KW00BXaHcNRg84AzSKZYSBfa8XWZdwg==', 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDg4XcqfIBWqopoEqAZ02YoSNAAYWZCgNo/tcrBvjQeyDWcGpUOgPej1bSmTxi4rbqqJRxJjxGMC6IILD3HIlDRPZkxz2H96AzxA7cQ5mLWs6vF8bJBb0/IdeDtxsB7VUxMWCGcMhMnM8lhL5xz04lcGGc7yiAXS2ko+Pseclt5AwIDAQAB', '2021-10-14 08:33:45', '2021-11-01 19:35:48', 1);
COMMIT;

-- ----------------------------
-- Table structure for gen_db
-- ----------------------------
DROP TABLE IF EXISTS `gen_db`;
CREATE TABLE `gen_db`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `db_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据库地址',
  `db_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '库名称',
  `db_type` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '数据库类型（0：mysql，1：mengoDb）',
  `driver_class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据库连接类（mysql：com.mysql.cj.jdbc.Driver）',
  `db_port` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据库端口',
  `user_name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码（私钥加密）',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '连接库明细';

-- ----------------------------
-- Records of gen_db
-- ----------------------------
BEGIN;
INSERT INTO `gen_db` VALUES (1, '127.0.0.1', 'nors-admin', 0, 'com.mysql.cj.jdbc.Driver', '3307', 'IndbCqQijeHUxlqCm3zbr9P69wr9M/76yQUJf8oxTY/tYPJvutQ2MBnnKvFc+BhAbAQRfXwvQMUxNo/gldSnJe8MkY4E7L2zi/HHup0UyRiXQN5tEK38e1vuaQUYLHSRebMQ/6RjBap/aDu4K2MnnS/IayVETO4B18/9jojKDuw=', 'UMNvq+ZK5oo5/LRWWNR1aSDlcRkQnr8Kqgc4ZZ+n5fUsxaUC2dfnSEJaODPtHUfzCP0/UaiInibSVU2o3CDDACox/xzoUmr6RJBYi0THH6oy9cu0xBVNmTNPNTUhPI1TCCwx0O+EQtaigyEt9pvS30yOTWIg9rY7Z7f1BL6FTjQ=', '2021-10-12 17:02:01', NULL), (2, '127.0.0.1', 'nors-pay-share', 0, 'com.mysql.cj.jdbc.Driver', '3307', 'IndbCqQijeHUxlqCm3zbr9P69wr9M/76yQUJf8oxTY/tYPJvutQ2MBnnKvFc+BhAbAQRfXwvQMUxNo/gldSnJe8MkY4E7L2zi/HHup0UyRiXQN5tEK38e1vuaQUYLHSRebMQ/6RjBap/aDu4K2MnnS/IayVETO4B18/9jojKDuw=', 'UMNvq+ZK5oo5/LRWWNR1aSDlcRkQnr8Kqgc4ZZ+n5fUsxaUC2dfnSEJaODPtHUfzCP0/UaiInibSVU2o3CDDACox/xzoUmr6RJBYi0THH6oy9cu0xBVNmTNPNTUhPI1TCCwx0O+EQtaigyEt9pvS30yOTWIg9rY7Z7f1BL6FTjQ=', '2021-10-12 17:02:36', NULL);
COMMIT;

-- ----------------------------
-- Table structure for gen_tables
-- ----------------------------
DROP TABLE IF EXISTS `gen_tables`;
CREATE TABLE `gen_tables`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `db_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据库名称',
  `table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表名称',
  `package_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '包路径',
  `module_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模块名',
  `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类名',
  `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '库表生成_表';

-- ----------------------------
-- Records of gen_tables
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for gen_tables_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_tables_column`;
CREATE TABLE `gen_tables_column`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `table_id` int(10) NOT NULL COMMENT '关联键',
  `column_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字段名',
  `column_comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字段描述',
  `column_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` mediumint(1) NULL DEFAULT NULL COMMENT '是否主键',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '=' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '库表生成_表字段明细';

-- ----------------------------
-- Records of gen_tables_column
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
