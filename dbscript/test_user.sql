/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50168
Source Host           : localhost:3306
Source Database       : javafunc

Target Server Type    : MYSQL
Target Server Version : 50168
File Encoding         : 65001

Date: 2017-11-09 17:35:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `test_user`
-- ----------------------------
DROP TABLE IF EXISTS `test_user`;
CREATE TABLE `test_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(40) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test_user
-- ----------------------------
INSERT INTO `test_user` VALUES ('1', '测试', 'sfasgfaf', '24');
