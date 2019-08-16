/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : backend_basic

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 16/08/2019 16:18:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for core_dataauth_rule_mapper_scf
-- ----------------------------
DROP TABLE IF EXISTS `core_dataauth_rule_mapper_scf`;
CREATE TABLE `core_dataauth_rule_mapper_scf` (
  `id` varchar(32) NOT NULL COMMENT '唯一ID',
  `rule_id` varchar(32) DEFAULT NULL COMMENT '规则id',
  `rule_name` varchar(32) DEFAULT NULL COMMENT '规则名称',
  `dimension_type_id` varchar(64) DEFAULT NULL COMMENT '维度类别id',
  `dimension_type` varchar(64) DEFAULT NULL COMMENT '维度类别',
  `dimension_type_desc` varchar(64) DEFAULT NULL COMMENT '维度描述',
  `table_prefix` varchar(64) DEFAULT NULL COMMENT '表名前缀',
  `goal_field_no` varchar(32) DEFAULT NULL COMMENT '目标字段名称',
  `source_field_no` varchar(32) DEFAULT NULL COMMENT '来源字段名称',
  `is_enable` tinyint(4) DEFAULT NULL COMMENT '是否启用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建用户',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建用户名',
  `op_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `op_user` varchar(32) DEFAULT NULL COMMENT '修改用户',
  `op_user_name` varchar(30) DEFAULT NULL COMMENT '修改用户名',
  `remark` varchar(64) DEFAULT NULL COMMENT '描述',
  `attr1` varchar(64) DEFAULT NULL COMMENT '备用字段1',
  `attr2` varchar(64) DEFAULT NULL COMMENT '备用字段2',
  `attr3` varchar(64) DEFAULT NULL COMMENT '备用字段3',
  `attr4` varchar(64) DEFAULT NULL COMMENT '备用字段4',
  `deleted` smallint(4) DEFAULT '0' COMMENT '是否删除',
  `version` int(11) DEFAULT '0' COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则数据权限Mapper明细表';

-- ----------------------------
-- Table structure for core_dataauth_rule_scf
-- ----------------------------
DROP TABLE IF EXISTS `core_dataauth_rule_scf`;
CREATE TABLE `core_dataauth_rule_scf` (
  `id` varchar(32) NOT NULL COMMENT '唯一ID',
  `rule_name` varchar(32) DEFAULT NULL COMMENT '规则名称',
  `rule_desc` varchar(32) DEFAULT NULL COMMENT '规则描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建用户',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建用户名',
  `op_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `op_user` varchar(32) DEFAULT NULL COMMENT '修改用户',
  `op_user_name` varchar(30) DEFAULT NULL COMMENT '修改用户名',
  `remark` varchar(64) DEFAULT NULL COMMENT '描述',
  `attr1` varchar(64) DEFAULT NULL COMMENT '备用字段1',
  `attr2` varchar(64) DEFAULT NULL COMMENT '备用字段2',
  `attr3` varchar(64) DEFAULT NULL COMMENT '备用字段3',
  `attr4` varchar(64) DEFAULT NULL COMMENT '备用字段4',
  `deleted` smallint(4) DEFAULT '0' COMMENT '是否删除',
  `version` int(11) DEFAULT '0' COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则定义表';

SET FOREIGN_KEY_CHECKS = 1;
