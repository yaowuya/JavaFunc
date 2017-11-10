/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50168
Source Host           : localhost:3306
Source Database       : javafunc

Target Server Type    : MYSQL
Target Server Version : 50168
File Encoding         : 65001

Date: 2017-11-10 10:33:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `datasource`
-- ----------------------------
DROP TABLE IF EXISTS `datasource`;
CREATE TABLE `datasource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT '数据源名称',
  `type` varchar(100) DEFAULT NULL COMMENT '数据库类型',
  `rac` varchar(1) DEFAULT NULL COMMENT '是否rac',
  `url` varchar(200) DEFAULT NULL,
  `port` varchar(200) DEFAULT NULL,
  `database` varchar(200) DEFAULT NULL,
  `username` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of datasource
-- ----------------------------
