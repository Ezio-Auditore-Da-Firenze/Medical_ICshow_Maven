/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : medicalxsm

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 26/02/2020 21:41:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for doctorinfo
-- ----------------------------
DROP TABLE IF EXISTS `doctorinfo`;
CREATE TABLE `doctorinfo`  (
  `dno` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '医生编号',
  `dname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `dsex` int(1) NOT NULL COMMENT '性别',
  `departdes` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dsexdes` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `department` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属科室',
  `positiondes` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `position` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职务级别',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `count` int(10) NOT NULL COMMENT '看诊人数',
  PRIMARY KEY (`dno`) USING BTREE,
  INDEX `dname`(`dname`) USING BTREE,
  INDEX `department`(`department`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of doctorinfo
-- ----------------------------
INSERT INTO `doctorinfo` VALUES ('DC2017070003', '张晟铭', 0, '内科', '女', '2', '专家', '4', '13146285110', 38);
INSERT INTO `doctorinfo` VALUES ('DC2018030004', '王二', 1, '五官科', '男', '0', '主治医师', '0', '13146825119', 0);
INSERT INTO `doctorinfo` VALUES ('DC2018030005', '码子', 1, '外科', '男', '1', '副主治医师', '2', '15137185433', 2);
INSERT INTO `doctorinfo` VALUES ('DC2018030006', '李四', 1, '内科', '男', '2', '主任', '3', '13298760810', 5);
INSERT INTO `doctorinfo` VALUES ('DC2020020007', '尤睿琦', 1, ' 内科', '男', '2', '主任', '3', '15049251971', 5);
INSERT INTO `doctorinfo` VALUES ('DC2020020008', 'd\'sa\'da\'s', 1, '五官科', '男', '0', '手术师', '1', '15049251971', 1);

-- ----------------------------
-- Table structure for intelligentcontract
-- ----------------------------
DROP TABLE IF EXISTS `intelligentcontract`;
CREATE TABLE `intelligentcontract`  (
  `addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`addr`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of intelligentcontract
-- ----------------------------
INSERT INTO `intelligentcontract` VALUES ('0x45ed26cafce1665ba31f6b6eed56ddd13049f0bf');

-- ----------------------------
-- Table structure for patientinfo
-- ----------------------------
DROP TABLE IF EXISTS `patientinfo`;
CREATE TABLE `patientinfo`  (
  `pno` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `identity` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `psexdes` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `psex` int(1) NOT NULL,
  `age` int(3) NOT NULL,
  `dname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `departdes` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `department` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `totalcost` double(180, 2) NOT NULL,
  `treatdate` date NOT NULL,
  `arrivedate` date NULL DEFAULT NULL,
  `notes` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`pno`) USING BTREE,
  INDEX `dname`(`dname`) USING BTREE,
  INDEX `fpkde`(`department`) USING BTREE,
  CONSTRAINT `fpk_dname` FOREIGN KEY (`dname`) REFERENCES `doctorinfo` (`dname`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fpkde` FOREIGN KEY (`department`) REFERENCES `doctorinfo` (`department`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of patientinfo
-- ----------------------------
INSERT INTO `patientinfo` VALUES ('03a0f6d9', '新增2', '150203199805062266', '女', 0, 21, '张晟铭', '内科', '2', 255.00, '2020-02-18', '2020-02-18', '444666');
INSERT INTO `patientinfo` VALUES ('1aaf3d10', '李四', '412822199105227991', '男', 1, 28, '张晟铭', '内科', '2', 500.00, '2020-02-18', '2020-02-28', '111');
INSERT INTO `patientinfo` VALUES ('6cac4c4a', '测试1', '150203199709065522', '女', 0, 22, '张晟铭', '内科', '2', 32.00, '2020-02-18', '2020-02-18', '123123');
INSERT INTO `patientinfo` VALUES ('9efd2973', 'ceceshsh', '150203116603032255', '男', 1, 853, '张晟铭', '内科', '2', 456456.00, '2020-02-24', '2020-02-24', '3366');
INSERT INTO `patientinfo` VALUES ('d1a2d5cb', 'xxx', '150205199905062323', '女', 0, 20, '张晟铭', '内科', '2', 556.00, '2020-02-18', '2020-02-18', '111222');
INSERT INTO `patientinfo` VALUES ('d2d6b729', '许密', '412822199105227991', '男', 1, 26, '李四', '内科', '2', 123.00, '2018-03-29', '2018-03-29', '佛挡杀佛');
INSERT INTO `patientinfo` VALUES ('f01be817', 'zzz', '150022159192345461', '女', 0, 428, '张晟铭', '内科', '2', 5566.00, '2020-02-18', '2020-02-18', '123123');

SET FOREIGN_KEY_CHECKS = 1;
