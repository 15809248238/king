/*
Navicat MySQL Data Transfer

Source Server         : 本地连接
Source Server Version : 50638
Source Host           : localhost:3306
Source Database       : king

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2018-04-10 21:26:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tab_accounting
-- ----------------------------
DROP TABLE IF EXISTS `tab_accounting`;
CREATE TABLE `tab_accounting` (
  `accountID` int(11) NOT NULL AUTO_INCREMENT,
  `customerID` int(11) DEFAULT NULL,
  `money` int(11) DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  `balance` int(11) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`accountID`),
  KEY `FK_Reference_8` (`customerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_accounting
-- ----------------------------

-- ----------------------------
-- Table structure for tab_cargo
-- ----------------------------
DROP TABLE IF EXISTS `tab_cargo`;
CREATE TABLE `tab_cargo` (
  `cargoID` int(11) NOT NULL AUTO_INCREMENT,
  `cargoname` varchar(50) DEFAULT NULL,
  `cargotypename` varchar(50) DEFAULT NULL,
  `inprice` int(11) DEFAULT NULL,
  `outprice` int(11) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `creattime` varchar(50) DEFAULT NULL,
  `modifier` varchar(50) DEFAULT NULL,
  `modifytime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cargoID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_cargo
-- ----------------------------
INSERT INTO `tab_cargo` VALUES ('1', '戴尔主机', '主机', '3000', '5600', 'null', '2018-03-27', 'king', '2018-03-27');
INSERT INTO `tab_cargo` VALUES ('2', '戴尔显示器', '主机', '1000', '1500', 'king', '2018-04-05', 'king', '2018-04-05');

-- ----------------------------
-- Table structure for tab_customer
-- ----------------------------
DROP TABLE IF EXISTS `tab_customer`;
CREATE TABLE `tab_customer` (
  `customerID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `sex` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `creattime` varchar(50) DEFAULT NULL,
  `modifier` varchar(50) DEFAULT NULL,
  `modifytime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`customerID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_customer
-- ----------------------------
INSERT INTO `tab_customer` VALUES ('1', 'king', '女', '北京北京', '15809348348', '31231@qq.com', 'null', '2018-03-26', 'king', '2018-04-06');

-- ----------------------------
-- Table structure for tab_department
-- ----------------------------
DROP TABLE IF EXISTS `tab_department`;
CREATE TABLE `tab_department` (
  `departmentID` int(11) NOT NULL AUTO_INCREMENT,
  `parentdepartname` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `manager` varchar(50) DEFAULT NULL,
  `departmentphone` varchar(50) DEFAULT NULL,
  `employeecount` int(11) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `creattime` varchar(50) DEFAULT NULL,
  `modifier` varchar(50) DEFAULT NULL,
  `modifytime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`departmentID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_department
-- ----------------------------
INSERT INTO `tab_department` VALUES ('1', '', '企业部', 'king', '029-1234567', '12', 'king', '2018-03-12', 'king', '2018-03-24');
INSERT INTO `tab_department` VALUES ('3', '企业部', '企业一部', '小明', '029-1234568', '5', '小明', '2018-03-13', 'king', '2018-04-10');
INSERT INTO `tab_department` VALUES ('5', '', '市场部', 'king', '029-1234789', '3', 'king', '2018-03-13', 'king', '2018-04-05');

-- ----------------------------
-- Table structure for tab_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `tab_dictionary`;
CREATE TABLE `tab_dictionary` (
  `types` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tab_dictionary
-- ----------------------------
INSERT INTO `tab_dictionary` VALUES ('cargoType', '主机');
INSERT INTO `tab_dictionary` VALUES ('employeeType', '人事管理员');
INSERT INTO `tab_dictionary` VALUES ('employeeType', '仓库管理员');
INSERT INTO `tab_dictionary` VALUES ('employeeType', '入库管理员');
INSERT INTO `tab_dictionary` VALUES ('employeeType', '出库管理员');
INSERT INTO `tab_dictionary` VALUES ('employeeType', '客户管理员');
INSERT INTO `tab_dictionary` VALUES ('cargoType', '显示器');
INSERT INTO `tab_dictionary` VALUES ('employeeType', '系统管理员');
INSERT INTO `tab_dictionary` VALUES ('employeeType', '货物管理员');
INSERT INTO `tab_dictionary` VALUES ('employeeType', '部门管理员');
INSERT INTO `tab_dictionary` VALUES ('cargoType', '键盘');
INSERT INTO `tab_dictionary` VALUES ('cargoType', '鼠标');

-- ----------------------------
-- Table structure for tab_employee
-- ----------------------------
DROP TABLE IF EXISTS `tab_employee`;
CREATE TABLE `tab_employee` (
  `employeeID` int(11) NOT NULL AUTO_INCREMENT,
  `departmentName` varchar(50) DEFAULT NULL,
  `employeeName` varchar(50) DEFAULT NULL,
  `sex` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `birthday` varchar(50) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `creattime` varchar(50) DEFAULT NULL,
  `modifier` varchar(50) DEFAULT NULL,
  `modifytime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`employeeID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_employee
-- ----------------------------
INSERT INTO `tab_employee` VALUES ('1', '人事部', '小华', '男', '西安市', '15809248238', '人事管理员', '1875981271@qq.com', '1996-10-02', 'king', '2018-03-24', 'king', '2018-04-05');
INSERT INTO `tab_employee` VALUES ('3', '企业一部', 'root', '男', '北京', '15809248238', '系统管理员', '1875981271@qq.com', '1997-09-09', 'king', '2018-03-24', 'king', '2018-04-06');
INSERT INTO `tab_employee` VALUES ('4', '企业一部', '李博', '男', '陕西西安', '15809248238', '系统管理员', '1875981271@qq.com', '1996-10-02', 'king', '2018-04-07', 'king', '2018-04-07');

-- ----------------------------
-- Table structure for tab_inventory
-- ----------------------------
DROP TABLE IF EXISTS `tab_inventory`;
CREATE TABLE `tab_inventory` (
  `inventoryID` int(11) NOT NULL AUTO_INCREMENT,
  `warehouseID` int(11) DEFAULT NULL,
  `cargoID` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `creattime` varchar(50) DEFAULT NULL,
  `modifier` varchar(50) DEFAULT NULL,
  `modifytime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`inventoryID`),
  KEY `FK_Reference_2` (`warehouseID`),
  KEY `FK_Reference_3` (`cargoID`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`cargoID`) REFERENCES `tab_cargo` (`cargoID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_inventory
-- ----------------------------
INSERT INTO `tab_inventory` VALUES ('1', '1', '1', '400', 'king', '2018-04-01', 'king', '2018-04-01');
INSERT INTO `tab_inventory` VALUES ('2', '1', '2', '400', 'king', '2018-04-05', 'king', '2018-04-05');

-- ----------------------------
-- Table structure for tab_purchaseorder
-- ----------------------------
DROP TABLE IF EXISTS `tab_purchaseorder`;
CREATE TABLE `tab_purchaseorder` (
  `purchaseorderID` int(11) NOT NULL AUTO_INCREMENT,
  `customerID` int(11) DEFAULT NULL,
  `warehouseID` int(11) DEFAULT NULL,
  `cargoID` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`purchaseorderID`),
  KEY `FK_Reference_10` (`cargoID`),
  KEY `FK_Reference_6` (`warehouseID`),
  KEY `FK_Reference_9` (`customerID`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`cargoID`) REFERENCES `tab_cargo` (`cargoID`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`customerID`) REFERENCES `tab_customer` (`customerID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_purchaseorder
-- ----------------------------
INSERT INTO `tab_purchaseorder` VALUES ('1', '1', '1', '1', '100', '2018-04-03', 'commited');
INSERT INTO `tab_purchaseorder` VALUES ('2', '1', '1', '2', '200', '2018-04-05', 'commited');

-- ----------------------------
-- Table structure for tab_saleorder
-- ----------------------------
DROP TABLE IF EXISTS `tab_saleorder`;
CREATE TABLE `tab_saleorder` (
  `saleorderID` int(11) NOT NULL AUTO_INCREMENT,
  `customerID` int(11) DEFAULT NULL,
  `warehouseID` int(11) DEFAULT NULL,
  `cargoID` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`saleorderID`),
  KEY `FK_Reference_11` (`customerID`),
  KEY `FK_Reference_12` (`cargoID`),
  KEY `FK_Reference_7` (`warehouseID`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`customerID`) REFERENCES `tab_customer` (`customerID`),
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`cargoID`) REFERENCES `tab_cargo` (`cargoID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_saleorder
-- ----------------------------
INSERT INTO `tab_saleorder` VALUES ('5', '1', '2', '2', '500', '2018-04-05', 'uncommitted');
INSERT INTO `tab_saleorder` VALUES ('6', '1', '1', '1', '200', '2018-04-05', 'commited');

-- ----------------------------
-- Table structure for tab_user
-- ----------------------------
DROP TABLE IF EXISTS `tab_user`;
CREATE TABLE `tab_user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_user
-- ----------------------------
INSERT INTO `tab_user` VALUES ('2', 'king', '123456');
INSERT INTO `tab_user` VALUES ('3', '123', '123456');
INSERT INTO `tab_user` VALUES ('4', '15809248238', '123456');

-- ----------------------------
-- Table structure for tab_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `tab_warehouse`;
CREATE TABLE `tab_warehouse` (
  `warehouseID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `manager` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `creattime` varchar(50) DEFAULT NULL,
  `modifier` varchar(50) DEFAULT NULL,
  `modifytime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`warehouseID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_warehouse
-- ----------------------------
INSERT INTO `tab_warehouse` VALUES ('1', '一号仓库', 'king', '西安市长安区', '使用中', 'null', '2018-03-29', 'king', '2018-04-07');
INSERT INTO `tab_warehouse` VALUES ('2', '二号仓库', 'king', '未央区', '使用中', 'king', '2018-04-07', 'null', 'null');
