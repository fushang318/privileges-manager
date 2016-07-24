/*
Navicat MySQL Data Transfer

Source Server         : spm_localhost
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : ylb_privileges

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2016-07-24 21:54:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bas_menu`
-- ----------------------------
DROP TABLE IF EXISTS `bas_menu`;
CREATE TABLE `bas_menu` (
  `menu_id` int(10) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(20) DEFAULT NULL,
  `menu_url` varchar(100) DEFAULT NULL,
  `parent_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bas_menu
-- ----------------------------
INSERT INTO `bas_menu` VALUES ('1', '系统管理', '', null);
INSERT INTO `bas_menu` VALUES ('2', '业务管理', '', null);
INSERT INTO `bas_menu` VALUES ('3', '财务管理', '', null);
INSERT INTO `bas_menu` VALUES ('5', '角色管理', 'role.do', '1');
INSERT INTO `bas_menu` VALUES ('6', '用户管理', 'user.do', '1');
INSERT INTO `bas_menu` VALUES ('7', '菜单管理', 'menu.do', '1');
INSERT INTO `bas_menu` VALUES ('8', 'aaa', 'user.do', '2');
INSERT INTO `bas_menu` VALUES ('9', 'bbb', 'user.do', '2');
INSERT INTO `bas_menu` VALUES ('10', '111', 'user.do', '3');
INSERT INTO `bas_menu` VALUES ('11', '222', 'user.do', '3');
INSERT INTO `bas_menu` VALUES ('12', '333', 'user.do', '3');
INSERT INTO `bas_menu` VALUES ('15', 'ccc', 'user.do', '2');
INSERT INTO `bas_menu` VALUES ('16', '后台管理', '', null);
INSERT INTO `bas_menu` VALUES ('17', '11111', 'user.do', '16');
INSERT INTO `bas_menu` VALUES ('18', '22222', 'user.do', '16');
INSERT INTO `bas_menu` VALUES ('19', '33333', 'user.do', '16');

-- ----------------------------
-- Table structure for `bas_role`
-- ----------------------------
DROP TABLE IF EXISTS `bas_role`;
CREATE TABLE `bas_role` (
  `role_id` int(10) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) DEFAULT NULL,
  `rights` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bas_role
-- ----------------------------
INSERT INTO `bas_role` VALUES ('1', '系统管理员', '35822');
INSERT INTO `bas_role` VALUES ('2', '普通用户', '2038792');
INSERT INTO `bas_role` VALUES ('3', '系统用户', '230374');

-- ----------------------------
-- Table structure for `bas_user`
-- ----------------------------
DROP TABLE IF EXISTS `bas_user`;
CREATE TABLE `bas_user` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `role_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bas_user
-- ----------------------------
INSERT INTO `bas_user` VALUES ('1', 'admin', '1', '管理员', '0', '1');
INSERT INTO `bas_user` VALUES ('2', 'user1', '1', '用户A1a', '0', '2');
INSERT INTO `bas_user` VALUES ('11', 'user2', '1', 'afdsf', '0', '3');
INSERT INTO `bas_user` VALUES ('36', 'user3', '1', '33331', '0', null);
