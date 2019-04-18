/*
Navicat MySQL Data Transfer

Source Server         : a
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : his_register

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2017-01-02 13:02:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `his_department`
-- ----------------------------
DROP TABLE IF EXISTS `his_department`;
CREATE TABLE `his_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of his_department
-- ----------------------------
INSERT INTO `his_department` VALUES ('1', '外科');
INSERT INTO `his_department` VALUES ('2', '内科');
INSERT INTO `his_department` VALUES ('4', '神经科');

-- ----------------------------
-- Table structure for `his_doctor`
-- ----------------------------
DROP TABLE IF EXISTS `his_doctor`;
CREATE TABLE `his_doctor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `desciption` varchar(200) DEFAULT NULL,
  `real_name` varchar(10) DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL COMMENT '部门id',
  `position` varchar(10) DEFAULT NULL COMMENT '职称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of his_doctor
-- ----------------------------
INSERT INTO `his_doctor` VALUES ('1', '高级医师', '周杰伦', '1', '1', '高级医师');
INSERT INTO `his_doctor` VALUES ('2', '权威', '无限', '1', '2', '按时');
INSERT INTO `his_doctor` VALUES ('3', '权威', '阿斯蒂', '1', '1', '恩恩');
INSERT INTO `his_doctor` VALUES ('4', '123', '3123', '0', '4', '1234');

-- ----------------------------
-- Table structure for `his_patient`
-- ----------------------------
DROP TABLE IF EXISTS `his_patient`;
CREATE TABLE `his_patient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `identity_card` varchar(18) DEFAULT NULL COMMENT '身份证',
  `name` varchar(10) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `age` smallint(6) DEFAULT NULL,
  `medical_card` varchar(20) DEFAULT NULL COMMENT '就诊卡',
  `sex` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of his_patient
-- ----------------------------
INSERT INTO `his_patient` VALUES ('1', '442000111111111111', '气味', '13250490622', '12', '442000111111111111', '1');
INSERT INTO `his_patient` VALUES ('2', '123412341234123412', '切勿', '13250490621', '13', '123412341234123412', '0');

-- ----------------------------
-- Table structure for `his_register`
-- ----------------------------
DROP TABLE IF EXISTS `his_register`;
CREATE TABLE `his_register` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL COMMENT '哪一天',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of his_register
-- ----------------------------
INSERT INTO `his_register` VALUES ('1', '2', '1', '1', '2016-11-18 21:19:22', '0', '1');
INSERT INTO `his_register` VALUES ('2', '1', '4', '4', '2016-11-18 23:21:43', '0', '1');
INSERT INTO `his_register` VALUES ('3', '2', '3', '1', '2016-11-18 23:21:56', '0', '1');

-- ----------------------------
-- Table structure for `moudle`
-- ----------------------------
DROP TABLE IF EXISTS `moudle`;
CREATE TABLE `moudle` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(40) DEFAULT NULL,
  `url` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of moudle
-- ----------------------------

-- ----------------------------
-- Table structure for `resources`
-- ----------------------------
DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '父资源id',
  `name` varchar(40) NOT NULL COMMENT '名字',
  `url` varchar(100) NOT NULL COMMENT '地址',
  `description` varchar(40) NOT NULL COMMENT '描述',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `type_id` int(11) NOT NULL COMMENT '资源类型id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `seq` int(11) NOT NULL COMMENT '排序',
  `icon` varchar(40) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8 COMMENT='资源表';

-- ----------------------------
-- Records of resources
-- ----------------------------
INSERT INTO `resources` VALUES ('1', '0', '用户管理', '/user', '', '1', '1', '2016-10-09 16:59:55', '2', '');
INSERT INTO `resources` VALUES ('2', '1', '用户新建', '/user/add', '', '1', '1', '2016-10-10 11:13:42', '3', '');
INSERT INTO `resources` VALUES ('3', '0', '角色管理', '/role', '', '1', '1', '2016-10-10 11:14:24', '3', '');
INSERT INTO `resources` VALUES ('4', '3', '角色新建', '/role/add', '', '1', '1', '2016-10-10 11:15:26', '1', '');
INSERT INTO `resources` VALUES ('5', '1', '处理新建', '/user/doadd', '', '1', '3', '2016-10-10 11:19:56', '1', '');
INSERT INTO `resources` VALUES ('6', '0', '资源管理', '/resource', '', '1', '1', '2016-10-09 16:59:55', '4', '');
INSERT INTO `resources` VALUES ('7', '6', '资源列表', '/resource/list', '', '1', '1', '2016-10-09 16:59:55', '1', '');
INSERT INTO `resources` VALUES ('8', '6', '资源新建', '/resource/add', '', '1', '1', '2016-10-09 16:59:55', '2', '');
INSERT INTO `resources` VALUES ('9', '6', '处理新建', '/resource/doadd', '', '1', '3', '2016-10-09 16:59:55', '4', '');
INSERT INTO `resources` VALUES ('10', '6', '资源编辑', '/resource/edit', '', '1', '4', '2016-10-09 16:59:55', '1', '');
INSERT INTO `resources` VALUES ('11', '6', '处理编辑', '/resource/doedit', '', '1', '3', '2016-10-09 16:59:55', '5', '');
INSERT INTO `resources` VALUES ('12', '1', '用户列表', '/user/list', '', '1', '1', '2016-11-15 23:38:29', '1', '');
INSERT INTO `resources` VALUES ('13', '1', '用户编辑', '/user/edit', '', '1', '4', '2016-10-10 11:19:56', '0', '');
INSERT INTO `resources` VALUES ('14', '1', '处理编辑', '/user/doedit', '', '1', '3', '2016-10-10 11:19:56', '0', '');
INSERT INTO `resources` VALUES ('15', '1', '用户删除', '/user/delete', '', '1', '3', '2016-10-10 11:19:56', '0', '');
INSERT INTO `resources` VALUES ('16', '3', '处理新建', '/role/doadd', '', '1', '3', '2016-10-10 11:15:26', '0', '');
INSERT INTO `resources` VALUES ('17', '3', '角色编辑', '/role/edit', '', '1', '4', '2016-10-10 11:15:26', '0', '');
INSERT INTO `resources` VALUES ('18', '3', '处理编辑', '/role/doedit', '', '1', '3', '2016-10-10 11:15:26', '0', '');
INSERT INTO `resources` VALUES ('19', '3', '角色列表', '/role/list', '', '1', '1', '2016-10-10 11:14:24', '0', '');
INSERT INTO `resources` VALUES ('20', '3', '角色删除', '/role/delete', '', '1', '3', '2016-10-10 11:15:26', '0', '');
INSERT INTO `resources` VALUES ('21', '6', '资源删除', '/resource/delete', '', '1', '3', '2016-10-10 11:14:24', '0', '');
INSERT INTO `resources` VALUES ('22', '3', '角色分配', '/role/grant', '', '1', '4', '2016-10-10 11:14:24', '0', '');
INSERT INTO `resources` VALUES ('23', '3', '处理分配', '/role/dogrant', '', '1', '3', '2016-10-10 11:14:24', '0', '');
INSERT INTO `resources` VALUES ('24', '25', '资源类型列表', '/resourcetype/list', '', '1', '1', '2016-10-10 11:14:24', '0', '');
INSERT INTO `resources` VALUES ('25', '0', '资源类型管理', '/resourcetype', '', '1', '1', '2016-10-10 11:14:24', '5', '');
INSERT INTO `resources` VALUES ('26', '25', '资源类型新建', '/resourcetype/add', '', '1', '1', '2016-10-10 11:14:24', '0', '');
INSERT INTO `resources` VALUES ('27', '25', '处理新建', '/resourcetype/doadd', '', '1', '3', '2016-10-10 11:14:24', '0', '');
INSERT INTO `resources` VALUES ('28', '25', '资源类型编辑', '/resourcetype/edit', '', '1', '4', '2016-10-10 11:14:24', '0', '');
INSERT INTO `resources` VALUES ('29', '25', '处理编辑', '/resourcetype/doedit', '', '1', '3', '2016-10-10 11:14:24', '0', '');
INSERT INTO `resources` VALUES ('30', '25', '资源类型删除', '/resourcetype/delete', '', '1', '3', '2016-10-10 11:14:24', '0', '');
INSERT INTO `resources` VALUES ('31', '0', '病人管理', '/patient', '', '1', '1', '2016-10-10 11:14:24', '8', '');
INSERT INTO `resources` VALUES ('32', '31', '病人列表', '/patient/list', '', '1', '1', '2016-10-10 11:14:24', '0', '');
INSERT INTO `resources` VALUES ('33', '31', '病人新建', '/patient/add', '', '1', '1', '2016-10-10 11:14:24', '0', '');
INSERT INTO `resources` VALUES ('34', '31', '处理新建', '/patient/doadd', '', '1', '3', '2016-10-10 11:14:24', '0', '');
INSERT INTO `resources` VALUES ('35', '31', '病人编辑', '/patient/edit', '', '1', '4', '2016-10-10 11:14:24', '0', '');
INSERT INTO `resources` VALUES ('36', '31', '处理编辑', '/patient/doedit', '', '1', '3', '2016-10-10 11:14:24', '0', '');
INSERT INTO `resources` VALUES ('37', '31', '病人删除', '/patient/delete', '', '1', '3', '2016-10-10 11:14:24', '0', '');
INSERT INTO `resources` VALUES ('39', '6', '资源一键创建', '/resource/autocreate', '', '1', '3', '2016-10-10 11:14:24', '0', '');
INSERT INTO `resources` VALUES ('49', '0', '科室管理', '/department', '', '1', '1', '2016-11-17 00:29:35', '6', '');
INSERT INTO `resources` VALUES ('50', '49', '科室列表', '/department/list', '', '1', '1', '2016-11-17 00:29:35', '0', '');
INSERT INTO `resources` VALUES ('51', '49', '科室新建', '/department/add', '', '1', '1', '2016-11-17 00:29:35', '0', '');
INSERT INTO `resources` VALUES ('52', '49', '处理新建', '/department/doadd', '', '1', '3', '2016-11-17 00:29:35', '0', '');
INSERT INTO `resources` VALUES ('53', '49', '科室编辑', '/department/edit', '', '1', '4', '2016-11-17 00:29:35', '0', '');
INSERT INTO `resources` VALUES ('54', '49', '处理编辑', '/department/doedit', '', '1', '3', '2016-11-17 00:29:35', '0', '');
INSERT INTO `resources` VALUES ('55', '49', '科室删除', '/department/delete', '', '1', '3', '2016-11-17 00:29:35', '0', '');
INSERT INTO `resources` VALUES ('56', '0', '医生管理', '/doctor', '', '1', '1', '2016-11-17 15:24:52', '7', '');
INSERT INTO `resources` VALUES ('57', '56', '医生列表', '/doctor/list', '', '1', '1', '2016-11-17 15:24:52', '0', '');
INSERT INTO `resources` VALUES ('58', '56', '医生新建', '/doctor/add', '', '1', '1', '2016-11-17 15:24:52', '0', '');
INSERT INTO `resources` VALUES ('59', '56', '处理新建', '/doctor/doadd', '', '1', '3', '2016-11-17 15:24:52', '0', '');
INSERT INTO `resources` VALUES ('60', '56', '医生编辑', '/doctor/edit', '', '1', '4', '2016-11-17 15:24:52', '0', '');
INSERT INTO `resources` VALUES ('61', '56', '处理编辑', '/doctor/doedit', '', '1', '3', '2016-11-17 15:24:52', '0', '');
INSERT INTO `resources` VALUES ('62', '56', '医生删除', '/doctor/delete', '', '1', '3', '2016-11-17 15:24:52', '0', '');
INSERT INTO `resources` VALUES ('63', '0', '挂号管理', '/register', '', '1', '1', '2016-11-18 00:38:33', '9', '');
INSERT INTO `resources` VALUES ('64', '63', '挂号列表', '/register/list', '', '1', '1', '2016-11-18 00:38:33', '0', '');
INSERT INTO `resources` VALUES ('65', '63', '挂号新建', '/register/add', '', '1', '1', '2016-11-18 00:38:33', '0', '');
INSERT INTO `resources` VALUES ('66', '63', '处理新建', '/register/doadd', '', '1', '3', '2016-11-18 00:38:33', '0', '');
INSERT INTO `resources` VALUES ('67', '63', '挂号编辑', '/register/edit', '', '1', '4', '2016-11-18 00:38:33', '0', '');
INSERT INTO `resources` VALUES ('68', '63', '处理编辑', '/register/doedit', '', '1', '3', '2016-11-18 00:38:33', '0', '');
INSERT INTO `resources` VALUES ('69', '63', '挂号删除', '/register/delete', '', '1', '3', '2016-11-18 00:38:33', '0', '');
INSERT INTO `resources` VALUES ('70', '31', '病人选择', '/patient/select', '', '1', '4', '2016-10-10 11:14:24', '0', '');
INSERT INTO `resources` VALUES ('71', '0', '会话管理', '/session', '', '1', '1', '2016-11-18 00:38:33', '0', '');
INSERT INTO `resources` VALUES ('72', '71', '会话列表', '/session/list', '', '1', '1', '0000-00-00 00:00:00', '0', '');
INSERT INTO `resources` VALUES ('73', '71', '会话踢出', '/session/kick', '踢出', '1', '3', '2016-11-23 21:15:24', '1', '');

-- ----------------------------
-- Table structure for `resource_type`
-- ----------------------------
DROP TABLE IF EXISTS `resource_type`;
CREATE TABLE `resource_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(40) DEFAULT NULL COMMENT '资源类型名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource_type
-- ----------------------------
INSERT INTO `resource_type` VALUES ('1', '菜单');
INSERT INTO `resource_type` VALUES ('2', '按钮');
INSERT INTO `resource_type` VALUES ('3', 'ajax请求');
INSERT INTO `resource_type` VALUES ('4', '页面');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(40) NOT NULL COMMENT '名字',
  `seq` int(11) NOT NULL COMMENT '顺序',
  `status` tinyint(4) NOT NULL COMMENT '状态,1正常,0停用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '院长', '1', '1', '2016-09-27 21:44:10');
INSERT INTO `role` VALUES ('2', '操作人员', '0', '1', '2016-09-27 21:59:14');
INSERT INTO `role` VALUES ('5', '管理员', '2', '1', '2016-10-10 15:44:44');

-- ----------------------------
-- Table structure for `role_operation`
-- ----------------------------
DROP TABLE IF EXISTS `role_operation`;
CREATE TABLE `role_operation` (
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `operation_id` int(11) NOT NULL COMMENT '权限操作id',
  PRIMARY KEY (`role_id`,`operation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_operation
-- ----------------------------

-- ----------------------------
-- Table structure for `role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `role_id` int(11) NOT NULL DEFAULT '0',
  `resource_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`role_id`,`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES ('1', '31');
INSERT INTO `role_resource` VALUES ('1', '32');
INSERT INTO `role_resource` VALUES ('1', '33');
INSERT INTO `role_resource` VALUES ('1', '34');
INSERT INTO `role_resource` VALUES ('1', '35');
INSERT INTO `role_resource` VALUES ('1', '36');
INSERT INTO `role_resource` VALUES ('1', '37');
INSERT INTO `role_resource` VALUES ('1', '49');
INSERT INTO `role_resource` VALUES ('1', '50');
INSERT INTO `role_resource` VALUES ('1', '51');
INSERT INTO `role_resource` VALUES ('1', '52');
INSERT INTO `role_resource` VALUES ('1', '53');
INSERT INTO `role_resource` VALUES ('1', '54');
INSERT INTO `role_resource` VALUES ('1', '55');
INSERT INTO `role_resource` VALUES ('1', '56');
INSERT INTO `role_resource` VALUES ('1', '57');
INSERT INTO `role_resource` VALUES ('1', '58');
INSERT INTO `role_resource` VALUES ('1', '59');
INSERT INTO `role_resource` VALUES ('1', '60');
INSERT INTO `role_resource` VALUES ('1', '61');
INSERT INTO `role_resource` VALUES ('1', '62');
INSERT INTO `role_resource` VALUES ('1', '63');
INSERT INTO `role_resource` VALUES ('1', '64');
INSERT INTO `role_resource` VALUES ('1', '65');
INSERT INTO `role_resource` VALUES ('1', '66');
INSERT INTO `role_resource` VALUES ('1', '67');
INSERT INTO `role_resource` VALUES ('1', '68');
INSERT INTO `role_resource` VALUES ('1', '69');
INSERT INTO `role_resource` VALUES ('1', '70');
INSERT INTO `role_resource` VALUES ('2', '31');
INSERT INTO `role_resource` VALUES ('2', '32');
INSERT INTO `role_resource` VALUES ('2', '33');
INSERT INTO `role_resource` VALUES ('2', '34');
INSERT INTO `role_resource` VALUES ('2', '35');
INSERT INTO `role_resource` VALUES ('2', '36');
INSERT INTO `role_resource` VALUES ('2', '37');
INSERT INTO `role_resource` VALUES ('2', '49');
INSERT INTO `role_resource` VALUES ('2', '50');
INSERT INTO `role_resource` VALUES ('2', '56');
INSERT INTO `role_resource` VALUES ('2', '57');
INSERT INTO `role_resource` VALUES ('2', '63');
INSERT INTO `role_resource` VALUES ('2', '64');
INSERT INTO `role_resource` VALUES ('2', '65');
INSERT INTO `role_resource` VALUES ('2', '66');
INSERT INTO `role_resource` VALUES ('2', '67');
INSERT INTO `role_resource` VALUES ('2', '68');
INSERT INTO `role_resource` VALUES ('2', '69');
INSERT INTO `role_resource` VALUES ('2', '70');
INSERT INTO `role_resource` VALUES ('5', '1');
INSERT INTO `role_resource` VALUES ('5', '2');
INSERT INTO `role_resource` VALUES ('5', '3');
INSERT INTO `role_resource` VALUES ('5', '5');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(64) NOT NULL,
  `email` varchar(40) NOT NULL,
  `name` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `sex` tinyint(2) DEFAULT '0',
  `age` tinyint(2) DEFAULT '0',
  `usertype` int(2) DEFAULT '0' COMMENT '0为普通用户,1为管理员',
  `status` tinyint(2) DEFAULT '0',
  `organization_id` int(11) DEFAULT '0',
  `createdate` datetime NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'asd@163.com', 'admin2', '4297f44b13955235245b2497399d7a93', '0', '25', '1', '0', '1', '2015-12-06 13:14:05', '18707173379');
INSERT INTO `user` VALUES ('13', 'snoopy', '', 'snoopy', '4297f44b13955235245b2497399d7a93', '0', '25', '0', '0', '3', '2015-10-01 13:12:07', '18707173376');
INSERT INTO `user` VALUES ('14', 'dreamlu', '', 'dreamlu', '4297f44b13955235245b2497399d7a93', '0', '25', '0', '0', '5', '2015-10-11 23:12:58', '18707173376');
INSERT INTO `user` VALUES ('15', 'test', '', 'test', '4297f44b13955235245b2497399d7a93', '0', '25', '0', '0', '6', '2015-12-06 13:13:03', '18707173376');
INSERT INTO `user` VALUES ('16', 'test2', '15311111111@qq.com', '院长', '4297f44b13955235245b2497399d7a93', '0', '25', '0', '0', '0', '2016-09-24 22:35:24', '15311111111');
INSERT INTO `user` VALUES ('17', 'test1', '15311111111@qq.com', '操作人员', '4297f44b13955235245b2497399d7a93', '0', '25', '0', '0', '0', '2016-09-24 22:44:12', '15311111111');

-- ----------------------------
-- Table structure for `user_admin`
-- ----------------------------
DROP TABLE IF EXISTS `user_admin`;
CREATE TABLE `user_admin` (
  `id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_admin
-- ----------------------------

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('16', '1');
INSERT INTO `user_role` VALUES ('17', '2');
